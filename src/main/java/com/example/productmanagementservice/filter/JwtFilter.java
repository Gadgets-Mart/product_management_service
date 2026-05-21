package com.example.productmanagementservice.filter;

import com.example.productmanagementservice.dto.tokenDto.TokenValidationResponseDto;
import com.example.productmanagementservice.service.TokenValidationService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final TokenValidationService tokenValidationService;

    @Override
    public boolean shouldNotFilter(HttpServletRequest request){
        String requestUrl=request.getServletPath();
        return  requestUrl.contains("/api/rating/get");
    }




    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {



        //extracting the header
        String auth=request.getHeader("Authorization");

        if(auth==null || !auth.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }

        String token=auth.substring(7);
        String email=null;
        TokenValidationResponseDto responseDto=tokenValidationService.tokenValidationResponseDto(token);
         if(!responseDto.isValid()){
             response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
             response.getWriter().write("Token is invalid or expired");
             return;
         }


         //set authentciation
        UsernamePasswordAuthenticationToken authentication=
                new UsernamePasswordAuthenticationToken(
                         responseDto.getEmail(),  // principal(email
                        null,                  //credentials(not needed)
                        List.of(new SimpleGrantedAuthority(      //authorities
                                "ROLE_"+responseDto.getRole()
                        ))
                );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("Token is validated");
        filterChain.doFilter(request,response);
    }
}
