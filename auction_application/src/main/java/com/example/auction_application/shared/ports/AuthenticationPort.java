package com.example.auction_application.shared.ports;

public interface AuthenticationPort {
    Long getUserId(String token) throws Exception;    
}
