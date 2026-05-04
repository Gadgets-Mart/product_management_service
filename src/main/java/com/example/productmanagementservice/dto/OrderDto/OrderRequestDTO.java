package com.example.productmanagementservice.dto.OrderDto;


import lombok.Data;
import java.util.*;
@Data
public class OrderRequestDTO {
    private String userId;
    private List<OrderItemRequestDTO> items;

    // Shipping Details
    private String name;
    private String phone;
    private String address;
    private String city;
    private String state;
    private String pincode;
}
