package com.example.productmanagementservice.dto.OrderDto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemResponseDTO {
    private Integer productId;
    private Integer quantity;
    private Double priceAtPurchase;
}
