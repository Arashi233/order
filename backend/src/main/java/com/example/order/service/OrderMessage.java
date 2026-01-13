package com.example.order.service;

import com.example.order.model.Order;

public class OrderMessage {
    private String type; // CREATED | UPDATED
    private Order order;

    public OrderMessage() {}

    public OrderMessage(String type, Order order) {
        this.type = type;
        this.order = order;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}