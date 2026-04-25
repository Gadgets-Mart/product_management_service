package com.example.productmanagementservice.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Lob;
import lombok.Data;

@Embeddable
@Data
public class ProductImage {
    @Lob
    private byte[] image;
    private String imageName;
    private String contentType;
}
