package com.example.productmanagementservice.service;

import com.example.productmanagementservice.dto.tokenDto.TokenValidationResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class TokenValidationService {

    private final WebClient webClient;

    public TokenValidationResponseDto tokenValidationResponseDto(
            String token
    ){
        try {
            return webClient.get()
                    .uri("http://localhost:8081/api/auth/validate_token")
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
