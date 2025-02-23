package com.example.auction_application.shared.ports;

public interface AuctionPort {

    boolean userContainsItem(Long userId, Long itemId) throws Exception;
    void userWithIdExists(Long userId) throws Exception;
    void addToSellingAuctions(Long userId, Long auctionId) throws Exception; // adds auction id to user's selling auctions list.
    void addToOwned(Long userId, Long itemId) throws Exception;
    void removeFromOwned(Long userId, Long itemId) throws Exception; // removes item id from user's owned items list.
    void addToAuctioned(Long userId, Long itemId) throws Exception; // adds item id to user's auctioned items list.
    void removeFromAuctioned(Long userId, Long itemId) throws Exception;
}
