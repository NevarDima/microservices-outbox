package com.coffeshop.orders.controller;

import com.coffeshop.orders.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class OrdersController {

    private final OrdersService service;

    @PostMapping("/order")
    public Map<String,Object> createOrder(@RequestBody Map<String,Object> order) {
        return service.createOrder(order);
    }
}
