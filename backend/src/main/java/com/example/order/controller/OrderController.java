package com.example.order.controller;

import com.example.order.model.Order;
import com.example.order.model.OrderStatus;
import com.example.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {
    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody Map<String, Object> body) {
        Object itemsObj = body.get("items");
        if (!(itemsObj instanceof List)) {
            return ResponseEntity.badRequest().build();
        }
        @SuppressWarnings("unchecked")
        List<String> items = (List<String>) itemsObj;
        Order created = service.createOrder(items);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public List<Order> list(@RequestParam(required = false) String status) {
        Optional<OrderStatus> s = Optional.empty();
        if (status != null) {
            s = Optional.of(OrderStatus.valueOf(status));
        }
        return service.listOrders(s);
    }

    @PutMapping("/{id}/advance")
    public ResponseEntity<Order> advance(@PathVariable Long id) {
        try {
            Order updated = service.advanceOrder(id);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}