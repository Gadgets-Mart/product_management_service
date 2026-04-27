package com.example.productmanagementservice.dto.productDto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class ProductUpdateDto {
    private String name;
    private String category;
    private String brand;
    private Double price;
    private Double discount_price;
    private BigDecimal rating;
    private Boolean in_stock;
    private String description;
    private LocalDate created_at;
    private List<String> colors;
}
