package com.example.productmanagementservice.repository;

import com.example.productmanagementservice.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingInterface extends JpaRepository<Rating,String> {


    List<Rating>  findByProductId(String productId);

}
