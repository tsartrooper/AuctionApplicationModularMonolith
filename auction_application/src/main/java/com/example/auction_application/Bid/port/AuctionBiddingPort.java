package com.example.auction_application.Bid.port;

public interface AuctionBiddingPort {
    void validateBid(long auctionId, long bidderId, double amount) throws Exception;
    
    long getCurrentHighestBidderId(long auctionId) throws Exception;
    boolean isAuctionActive(long auctionId) throws Exception;
    void updateBid(long auctionId, long bidderId, double amount, long bidId) throws Exception;
}
