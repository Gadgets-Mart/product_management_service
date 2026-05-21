package com.example.productmanagementservice.dto.RatingModel;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RatingResponseDto {

    private String id;
    private String userId;
    private String productId;
    private int rating;
    private String text;
}