package com.example.productmanagementservice.service;

import com.example.productmanagementservice.model.Order;
import com.example.productmanagementservice.model.OrderStatus;
import com.example.productmanagementservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    OrderRepository repo;


    public Order getOrders(Long id) {
        return  repo.findById(id).orElse(null);
    }

    public Order createOrder(Order order) {
        if(order.getItems()!=null){
            order.getItems().forEach(item -> item.setOrder(order));
        }
       return  repo.save(order);

    }

    public Order cancelOrder(Long id) {
        Order a= repo.findById(id).orElse(null);
        if(a==null) return null;
        a.setStatus(OrderStatus.CANCELLED);
        return repo.save(a);
    }

    public Order updateStatus(Long id, OrderStatus status) {
        Order a= repo.findById(id).orElse(null);
        if(a==null) return null;
        a.setStatus(status);
        return repo.save(a);

    }
}
