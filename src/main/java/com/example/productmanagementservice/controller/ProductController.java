package com.example.productmanagementservice.controller;

import com.example.productmanagementservice.dto.productDto.ProductResponseDto;
import com.example.productmanagementservice.dto.productDto.ProductsResponseDto;
import com.example.productmanagementservice.model.Product;
import com.example.productmanagementservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    ProductService service;

    @PostMapping("/product")
    public ResponseEntity<ProductResponseDto> addProduct(@RequestPart Product product,@RequestPart MultipartFile[] images){
        try{
            ProductResponseDto pd = service.addProduct(product,images);
            return new ResponseEntity<>(pd, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ProductResponseDto("Failed:"+e.getMessage(),-1),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductsResponseDto>> getProducts(){
       List<ProductsResponseDto> list=service.findAll();
       if(list.size()>0)return new ResponseEntity<>(list,HttpStatus.OK);
       return new ResponseEntity<>(list,HttpStatus.NOT_FOUND);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductsResponseDto> getProductById(@PathVariable("id") int id){
        ProductsResponseDto prod=service.getProductById(id);
        if(prod==null)return new ResponseEntity<>(prod,HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(prod,HttpStatus.OK);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<ProductResponseDto> deleteProduct(@PathVariable("id") int id){
        ProductResponseDto prod=service.deleteProduct(id);
        if(prod==null)return new ResponseEntity<>(prod,HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(prod,HttpStatus.OK);
    }

//    @PatchMapping("/product/{id}")
//    public ResponseEntity<ProductsResponseDto> updateProduct(@PathVariable("id") int id){
//
//    }
}
