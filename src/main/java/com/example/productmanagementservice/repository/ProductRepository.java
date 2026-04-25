package com.example.productmanagementservice.repository;

import com.example.productmanagementservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  ProductRepository extends JpaRepository<Product,Integer> {
}
