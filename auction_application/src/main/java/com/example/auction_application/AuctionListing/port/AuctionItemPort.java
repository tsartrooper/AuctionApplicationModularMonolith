package com.example.auction_application.AuctionListing.port;

public interface AuctionItemPort {
    boolean userHasItem(Long userId, Long itemId) throws Exception;    
}
