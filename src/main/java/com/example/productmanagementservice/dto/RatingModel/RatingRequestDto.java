package com.example.productmanagementservice.dto.RatingModel;

import jakarta.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingRequestDto {

    @NotBlank(message = "User ID must not be blank")
    private String userId;

    @NotBlank(message = "Product ID must not be blank")
    private String productId;

    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must be at most 5")
    private int rating;

    @Size(max = 500, message = "Review text must not exceed 500 characters")
    private String text;

}
