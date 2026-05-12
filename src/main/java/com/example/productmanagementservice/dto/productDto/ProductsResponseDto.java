package com.example.productmanagementservice.dto.productDto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class ProductsResponseDto {
    int id;
    String name;
    String category;
    String brand;
    Double price;
    Double discount_price;
    BigDecimal rating;
    boolean in_stock;
    String description;
    LocalDate created_at;
    List<String> colors;
    int imageCount;
    Map<String,String> specifications;

    public String toString(){
        return name;
    }
}
