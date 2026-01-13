package com.example.order;

import org.springframework.stereotype.Component;

import com.example.order.repository.OrderRepository;

import jakarta.annotation.PostConstruct;

@Component
public class DataLoader {
    private final OrderRepository repo;

    public DataLoader(OrderRepository repo) {
        this.repo = repo;
    }

    @PostConstruct
    public void load() {
        // if (repo.count() == 0) {
        //     repo.save(new Order(Arrays.asList("宮保鶏肉", "ご飯"), OrderStatus.NEW));
        //     repo.save(new Order(Arrays.asList("麻婆豆腐（マーボー豆腐）"), OrderStatus.READY));
        // }
    }
}