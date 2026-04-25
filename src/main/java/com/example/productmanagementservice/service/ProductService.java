package com.example.productmanagementservice.service;

import com.example.productmanagementservice.dto.productDto.ProductResponseDto;
import com.example.productmanagementservice.dto.productDto.ProductsResponseDto;
import com.example.productmanagementservice.model.Product;
import com.example.productmanagementservice.model.ProductImage;
import com.example.productmanagementservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository repo;
    public ProductResponseDto addProduct(Product product, MultipartFile[] images) throws IOException {
        System.out.println(product);
        ArrayList<ProductImage> list = new ArrayList<>();
        for(MultipartFile image:images){
            ProductImage pd = new ProductImage();
            pd.setImageName(image.getOriginalFilename());
            pd.setContentType(image.getContentType());
            pd.setImage(image.getBytes());
            list.add(pd);
        }
        product.setCreated_at(LocalDate.now());
        product.setImages(list);
        repo.save(product);
        return new ProductResponseDto(product.getName(),product.getId());
    }

    public List<ProductsResponseDto> findAll() {
        List<Product> li=repo.findAll();
        List<ProductsResponseDto> res = new ArrayList<>();
        for(Product p:li){
            ProductsResponseDto dto=ProductsResponseDto
                    .builder()
                    .name(p.getName())
                    .id(p.getId())
                    .category(p.getCategory())
                    .brand(p.getBrand())
                    .price(p.getPrice())
                    .discount_price(p.getDiscount_price())
                    .rating(p.getRating())
                    .in_stock(p.isIn_stock())
                    .description(p.getDescription())
                    .created_at(p.getCreated_at())
                    .colors(p.getColors())
                    .imageCount(p.getImages().size())
                    .build();
            System.out.println(dto);
            res.add(dto);
        }
        return res;
    }

    public ProductsResponseDto getProductById(int id) {
        Product p=repo.findById(id).orElse(null);
        if(p==null)return null;
        return ProductsResponseDto
                .builder()
                .name(p.getName())
                .id(p.getId())
                .category(p.getCategory())
                .brand(p.getBrand())
                .price(p.getPrice())
                .discount_price(p.getDiscount_price())
                .rating(p.getRating())
                .in_stock(p.isIn_stock())
                .description(p.getDescription())
                .created_at(p.getCreated_at())
                .colors(p.getColors())
                .imageCount(p.getImages().size())
                .build();
    }

    public ProductResponseDto deleteProduct(int id) {
        Product p=repo.findById(id).orElse(null);
        if(p==null)return null;

        ProductResponseDto res = new ProductResponseDto(p.getName(),p.getId());
        repo.delete(p);
        return res;
    }
}
