package com.example.productmanagementservice.dto.OrderDto;

import lombok.Data;

@Data
public class OrderItemRequestDTO {
    private Integer productId;
    private Integer quantity;
}
