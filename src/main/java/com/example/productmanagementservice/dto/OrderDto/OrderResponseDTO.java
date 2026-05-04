package com.example.productmanagementservice.dto.OrderDto;

import com.example.productmanagementservice.model.OrderStatus;
import lombok.Data;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrderResponseDTO {
    private Long id;
    private String userId;
    private BigDecimal totalAmount;
    private OrderStatus status;
    private LocalDateTime createdAt;
    private List<OrderItemResponseDTO> items;
}
