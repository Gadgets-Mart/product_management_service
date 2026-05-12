package com.example.productmanagementservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String category;
    private String brand;
    private Double price;
    private Double discount_price;
    @Column(precision = 3,scale = 2)
    private BigDecimal rating;
    private boolean in_stock;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String description;
    private LocalDate created_at;
    @ElementCollection
    private List<String> colors;
    @ElementCollection
    private List<ProductImage> images;
    @ElementCollection
    private Map<String,String> specifications;
}
