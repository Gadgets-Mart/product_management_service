package com.example.productmanagementservice.repository;

import com.example.productmanagementservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findByCategory(String category);

    @Query("Select p from Product p where name like %:search% or category like %:search% or brand like %:search% or description like %:search%")
    List<Product> searchProduct(@Param("search") String search);
}
