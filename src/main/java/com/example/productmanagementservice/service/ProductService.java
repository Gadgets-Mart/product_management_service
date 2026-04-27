package com.example.productmanagementservice.service;

import com.example.productmanagementservice.dto.productDto.ProductResponseDto;
import com.example.productmanagementservice.dto.productDto.ProductUpdateDto;
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
import java.util.Optional;

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

    public ProductsResponseDto updateProduct(int id, ProductUpdateDto prod, MultipartFile[] images) throws IOException {
        Optional<Product> p = repo.findById(id);
        if(p.isEmpty())return null;
        Product pro = p.get();

        if(images!=null){
            List<ProductImage> list=new ArrayList<>();
            for(MultipartFile image:images){
                ProductImage pd = new ProductImage();
                pd.setImageName(image.getOriginalFilename());
                pd.setContentType(image.getContentType());
                pd.setImage(image.getBytes());
                list.add(pd);
            }
            pro.setImages(list);
        }
        if(prod!=null){
            if(prod.getName()!=null)pro.setName(prod.getName());
            if(prod.getCategory()!=null)pro.setCategory(prod.getCategory());
            if(prod.getBrand()!=null)pro.setBrand(prod.getBrand());
            if(prod.getPrice()!=null)pro.setPrice(prod.getPrice());
            if(prod.getDiscount_price()!=null)pro.setDiscount_price(prod.getDiscount_price());
            if(prod.getRating()!=null)pro.setRating(prod.getRating());
            if(prod.getIn_stock()!=null)pro.setIn_stock(prod.getIn_stock());
            if(prod.getDescription()!=null)pro.setDescription(prod.getDescription());
            if(prod.getCreated_at()!=null)pro.setCreated_at(prod.getCreated_at());
            if(prod.getColors()!=null)pro.setColors(prod.getColors());
        }

        repo.save(pro);
        return ProductsResponseDto
                .builder()
                .name(pro.getName())
                .id(pro.getId())
                .category(pro.getCategory())
                .brand(pro.getBrand())
                .price(pro.getPrice())
                .discount_price(pro.getDiscount_price())
                .rating(pro.getRating())
                .in_stock(pro.isIn_stock())
                .description(pro.getDescription())
                .created_at(pro.getCreated_at())
                .colors(pro.getColors())
                .imageCount(pro.getImages().size())
                .build();
    }

    public List<ProductsResponseDto> findByCategory(String category) {
        List<Product> li = repo.findByCategory(category);
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
            res.add(dto);
        }
        return res;
    }

    public List<ProductsResponseDto> searchProduct(String search) {
        List<Product> li = repo.searchProduct(search);
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
            res.add(dto);
        }
        return res;
    }
}
