package com.example.productmanagementservice.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ratings")
@Entity
public class Rating {

    @Id
    @UuidGenerator
    private String id;

    @Column(name="user_id",nullable = false)
    private String userId;

    @Column(name ="product_id",nullable = false)
    private String productId;

    @Column(name = "rating",nullable = false)
    private int rating;

    @Column(name = "text")
    private String text;

}
