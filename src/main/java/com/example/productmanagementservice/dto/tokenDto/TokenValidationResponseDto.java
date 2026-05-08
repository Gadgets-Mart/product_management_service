package com.example.productmanagementservice.dto.tokenDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenValidationResponseDto {

    private boolean valid;
    private String email;
    private String role;
    private String message;

}
