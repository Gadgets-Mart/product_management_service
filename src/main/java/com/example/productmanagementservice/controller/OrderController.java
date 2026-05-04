package com.example.productmanagementservice.controller;

import com.example.productmanagementservice.dto.OrderDto.OrderRequestDTO;
import com.example.productmanagementservice.dto.OrderDto.OrderResponseDTO;
import com.example.productmanagementservice.model.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import  java.util.*;
import com.example.productmanagementservice.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrder(@PathVariable Long id){
        OrderResponseDTO savedOrder=service.getOrderById(id);
        return ResponseEntity.ok(savedOrder) ;
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderRequestDTO order){

            OrderResponseDTO savedOrder = service.createOrder(order);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);

    }


    @PutMapping("/{id}/cancel")
    public ResponseEntity<?> cancelOrder(@PathVariable Long id){
        OrderResponseDTO savedOrder=service.cancelOrder(id);
        return savedOrder!=null ? ResponseEntity.ok(savedOrder) : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Unable to cancel Order ");

    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestParam OrderStatus status){
        OrderResponseDTO savedOrder=service.updateStatus(id,status);
        return savedOrder!=null ? ResponseEntity.ok(savedOrder) : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Unable to update Order status.");
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<List<OrderResponseDTO>> getHistory(@PathVariable String userId){
        List<OrderResponseDTO> history=service.getOrderHistory(userId);
        return ResponseEntity.ok(history);
    }
}
