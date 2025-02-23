package com.example.auction_application.Bid.port;

public interface UserBiddingPort {
    void addBid(Long userId, Long bidId) throws Exception;    
    void userWithIdExists(Long userId) throws Exception;
}
