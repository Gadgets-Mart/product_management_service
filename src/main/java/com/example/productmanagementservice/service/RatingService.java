package com.example.productmanagementservice.service;


import com.example.productmanagementservice.dto.RatingModel.RatingRequestDto;
import com.example.productmanagementservice.dto.RatingModel.RatingResponseDto;
import com.example.productmanagementservice.model.Rating;
import com.example.productmanagementservice.repository.RatingInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RatingService {

    private final RatingInterface repo;


    public RatingResponseDto addRating(RatingRequestDto dto) {
        Rating rating = new Rating();
        rating.setUserId(dto.getUserId());
        rating.setProductId(dto.getProductId());
        rating.setRating(dto.getRating());
        rating.setText(dto.getText());

        Rating saved = repo.save(rating);
        return mapToResponse(saved);
    }

    public List<RatingResponseDto> getRatingsByProduct(String productId) {
        return repo.findByProductId(productId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private RatingResponseDto mapToResponse(Rating rating) {
        return new RatingResponseDto(
                rating.getId(),
                rating.getUserId(),
                rating.getProductId(),
                rating.getRating(),
                rating.getText()
        );
    }
}
