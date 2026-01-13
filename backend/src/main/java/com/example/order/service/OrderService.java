package com.example.order.service;

import com.example.order.model.Order;
import com.example.order.model.OrderStatus;
import com.example.order.repository.OrderRepository;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository repository;
    private final SimpMessagingTemplate messagingTemplate;
    private final OrderPublisher publisher;

    public OrderService(OrderRepository repository, SimpMessagingTemplate messagingTemplate, OrderPublisher publisher) {
        this.repository = repository;
        this.messagingTemplate = messagingTemplate;
        this.publisher = publisher;
    }

    @Transactional
    public Order createOrder(List<String> items) {
        Order order = new Order(items, OrderStatus.NEW);
        Order saved = repository.save(order);
        // publish asynchronously
        publisher.publish(new OrderMessage("CREATED", saved));
        return saved;
    }

    public List<Order> listOrders(Optional<OrderStatus> status) {
        return status.map(repository::findByStatus).orElseGet(repository::findAll);
    }

    @Transactional
    public Order advanceOrder(Long id) {
        Order order = repository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        if (order.getStatus() == OrderStatus.NEW) {
            order.setStatus(OrderStatus.READY);
        } else if (order.getStatus() == OrderStatus.READY) {
            order.setStatus(OrderStatus.COMPLETED);
        } else {
            throw new RuntimeException("Order cannot be advanced");
        }
        Order saved = repository.save(order);
        // publish asynchronously
        publisher.publish(new OrderMessage("UPDATED", saved));
        return saved;
    }
}