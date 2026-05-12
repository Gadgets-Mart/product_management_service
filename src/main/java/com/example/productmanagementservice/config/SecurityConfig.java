package com.example.productmanagementservice.config;

import com.example.productmanagementservice.filter.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {


   private final JwtFilter jwtFilter;

      @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){
        http
                .csrf(c->c.disable())
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth->auth
                        .requestMatchers(HttpMethod.GET, "/product_api/**").permitAll()
                        .requestMatchers("/product_api/**").hasRole("ADMIN")
                        .requestMatchers("/orders/**").hasRole("CUSTOMER")
                        .requestMatchers(HttpMethod.GET,"/orders/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH,"/orders/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);


           return http.build();
    }

}
