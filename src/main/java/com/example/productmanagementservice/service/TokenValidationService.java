package com.example.productmanagementservice.service;

import com.example.productmanagementservice.dto.tokenDto.TokenValidationResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class TokenValidationService {

    private final WebClient webClient;

    @Value("${auth.validate.url}")
    private String validateUrl;

    public TokenValidationResponseDto tokenValidationResponseDto(
            String token
    ){
        try {
            return webClient.get()
                    .uri(validateUrl)
                    .header("Authorization", "Bearer " + token)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(TokenValidationResponseDto.class)
                    .block();
        }catch (Exception e){
            return new TokenValidationResponseDto(false, null, null,
                    "Validation failed: " + e.getMessage());        }
    }

}
