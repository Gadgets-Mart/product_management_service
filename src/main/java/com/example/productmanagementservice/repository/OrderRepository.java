package com.example.productmanagementservice.repository;

import com.example.productmanagementservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository  extends JpaRepository<Order,Long> {

}
