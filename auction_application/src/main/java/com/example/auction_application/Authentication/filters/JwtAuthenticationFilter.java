package com.example.auction_application.Authentication.filters;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.auction_application.Authentication.service.CustomUserDetailsService;
import com.example.auction_application.Authentication.utility.JwtUtils;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



// jwt authentication filter, used inside authentication responsibility chain.
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    CustomUserDetailsService userDetailsService;

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
                final String authorizationHeader = request.getHeader("Authorization");

                String username = null;
                String jwtToken = null;
                
                if(authorizationHeader != null 
                    && authorizationHeader.startsWith("Bearer ")){
                        jwtToken = authorizationHeader.substring(7);
                        try{
                            username = jwtUtils.extractClaims(jwtToken).getSubject();
                        }
                catch(ExpiredJwtException e){
                    System.out.println("Jwt token has expired.");
                }
                catch(Exception e){
                    System.out.println("Unable to get JWT Token");
                }
            }

            if(username != null
                && SecurityContextHolder.getContext().getAuthentication() == null){
                    UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

                    if(jwtUtils.validateToken(jwtToken, userDetails.getUsername())){
                        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
                                new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities());
                        usernamePasswordAuthenticationToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request));
                        
                        SecurityContextHolder.getContext()
                                            .setAuthentication(usernamePasswordAuthenticationToken);
                    }
            }
            
        chain.doFilter(request, response);
    }

    
}
