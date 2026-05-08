package com.example.productmanagementservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${auth.admin.url}")
    private String userManagementUrl;

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(userManagementUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE,   //send son
                        MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT,
                        MediaType.APPLICATION_JSON_VALUE)  //accept json
                .build();
    }

}
