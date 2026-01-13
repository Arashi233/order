package com.example.order.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class OrderPublisher {
    private final SimpMessagingTemplate messagingTemplate;

    public OrderPublisher(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Async("taskExecutor")
    public void publish(OrderMessage message) {
        messagingTemplate.convertAndSend("/topic/orders", message);
    }
}