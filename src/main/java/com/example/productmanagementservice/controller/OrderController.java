package com.example.productmanagementservice.controller;

import com.example.productmanagementservice.model.Order;
import com.example.productmanagementservice.model.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import  java.util.*;
import com.example.productmanagementservice.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService service;

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Long id){
         return service.getOrders(id);
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order){
         return service.createOrder(order);
    }

    @PutMapping("/{id}/cancel")
    public Order cancelOrder(@PathVariable Long id){
        return  service.cancelOrder(id);
    }

    @PatchMapping("/{id}/status")
    public Order updateStatus(@PathVariable Long id, @RequestParam OrderStatus status){
        return  service.updateStatus(id,status);
    }
}
