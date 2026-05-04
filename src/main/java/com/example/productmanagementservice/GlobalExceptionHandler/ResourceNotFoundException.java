package com.example.productmanagementservice.GlobalExceptionHandler;

public class ResourceNotFoundException extends RuntimeException{
    public  ResourceNotFoundException(String msg){
        super(msg);
    }
}
