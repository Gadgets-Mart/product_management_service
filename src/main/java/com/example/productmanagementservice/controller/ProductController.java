package com.example.productmanagementservice.controller;

import com.example.productmanagementservice.dto.productDto.ProductResponseDto;
import com.example.productmanagementservice.dto.productDto.ProductUpdateDto;
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
    public ResponseEntity<?> addProduct(@RequestPart Product product,@RequestPart MultipartFile[] images){
        try{
            ProductResponseDto pd = service.addProduct(product,images);
            return new ResponseEntity<>(pd, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error saving the Images",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/products")
    public ResponseEntity<?> getProducts(){
       List<ProductsResponseDto> list=service.findAll();
       if(!list.isEmpty())return new ResponseEntity<>(list,HttpStatus.OK);
       return new ResponseEntity<>("No products present",HttpStatus.NOT_FOUND);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") int id){
        ProductsResponseDto prod=service.getProductById(id);
        if(prod==null)return new ResponseEntity<>("Id is invalid",HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(prod,HttpStatus.OK);
    }

    @GetMapping("/productCategory/{category}")
    public ResponseEntity<?> getProductByCategory(@PathVariable("category") String category){
        List<ProductsResponseDto> list = service.findByCategory(category);
        if(list.isEmpty())return new ResponseEntity<>("Not a valid category",HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("/productSearch/{search}")
    public ResponseEntity<?> searchProduct(@PathVariable("search") String search){
        List<ProductsResponseDto> list = service.searchProduct(search);
        if(list.isEmpty())return new ResponseEntity<>("No items available for the keyword",HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") int id){
        ProductResponseDto prod=service.deleteProduct(id);
        if(prod==null)return new ResponseEntity<>("Id is invalid",HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(prod,HttpStatus.OK);
    }

    @PatchMapping("/product/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") int id, @RequestPart(required = false) ProductUpdateDto product, @RequestPart(required = false) MultipartFile[] images){
        try{
            ProductsResponseDto pd = service.updateProduct(id,product,images);
            if(pd==null) return new ResponseEntity<>("Id is invalid",HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(pd,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error saving the Images",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
