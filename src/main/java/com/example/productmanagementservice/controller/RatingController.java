package com.example.productmanagementservice.controller;

import com.example.productmanagementservice.dto.RatingModel.RatingRequestDto;
import com.example.productmanagementservice.dto.RatingModel.RatingResponseDto;
import com.example.productmanagementservice.service.RatingService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rating")
public class RatingController {
    private final RatingService service;

    @PostMapping("/add")
    public ResponseEntity<RatingResponseDto> addRating(
           @Valid @RequestBody RatingRequestDto request
    ){
         RatingResponseDto response=service.addRating(request);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/get/{productId}")
    public ResponseEntity<List<RatingResponseDto>> getRatingsByProduct(
            @PathVariable String productId) {
        List<RatingResponseDto> ratings = service.getRatingsByProduct(productId);
        return ResponseEntity.ok(ratings);
    }


}
