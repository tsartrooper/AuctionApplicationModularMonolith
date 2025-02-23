package com.example.auction_application.Authentication.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.auction_application.Authentication.utility.JwtUtils;
import com.example.auction_application.shared.ports.AuthenticationPort;


@Component
public class AuthenticationAdapter implements AuthenticationPort {
    
    @Autowired
    JwtUtils jwtUtils;

    @Override
    public Long getUserId(String token) throws Exception {
        return jwtUtils.extractUserId(token);
    }    
}
