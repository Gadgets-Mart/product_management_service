package com.example.productmanagementservice.config;

import  org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.rmi.registry.Registry;

@Configuration
public class CorsConfig {


    @Bean
    public WebMvcConfigurer webMvcConfigurer(){

        return new WebMvcConfigurer(){

            @Override
            public void addCorsMappings(CorsRegistry registry){
                   registry.addMapping("/**")
                           .allowedOrigins("http://localhost:9900")
                           .allowedMethods("GET","DELETE","PUT","POST","PATCH")
                           .allowedHeaders("*")
                           .allowCredentials(true);
            }

        };

    }
}
