package com.example.auction_application.UserModule.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.auction_application.Bid.port.UserBiddingPort;
import com.example.auction_application.UserModule.service.UserService;

@Component
public class UserBiddingAdapter implements UserBiddingPort{
    

    @Autowired
    UserService userService;

    @Override
    public void addBid(Long userId, Long bidId) throws Exception {
        userService.addBid(userId, bidId);        
    }
    
    @Override
    public void userWithIdExists(Long userId) throws Exception{
        userService.findById(userId);        
    }

}
