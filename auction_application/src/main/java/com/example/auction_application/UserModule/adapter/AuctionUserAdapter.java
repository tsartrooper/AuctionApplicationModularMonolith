package com.example.auction_application.UserModule.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.auction_application.UserModule.entity.WebUser;
import com.example.auction_application.UserModule.service.UserService;
import com.example.auction_application.shared.ports.AuctionPort;



@Component
public class AuctionUserAdapter implements AuctionPort {

    @Autowired
    UserService userService;

    public AuctionUserAdapter(UserService userService){
        this.userService = userService;
    }

    @Override
    public void userWithIdExists(Long userId) throws Exception {
        userService.findById(userId);
    }

    @Override
    public void addToSellingAuctions(Long userId, Long auctionId) throws Exception {
        userService.addSellingAuctionId(userId, auctionId);
    }

    @Override
    public boolean userContainsItem(Long userId, Long itemId) throws Exception {
        WebUser user = userService.findById(userId);
        return user.getOwnedItems().contains(itemId);
    }   

    @Override
    public void removeFromOwned(Long userId, Long itemId) throws Exception{
        userService.removeFromOwned(userId, itemId);
    }

    @Override
    public void addToAuctioned(Long userId, Long itemId)throws Exception{
        userService.addToAuctioned(userId, itemId);
    }

    @Override
    public void addToOwned(Long userId, Long itemId) throws Exception {
        userService.addToOwned(userId, itemId);
    }

    @Override
    public void removeFromAuctioned(Long userId, Long itemId) throws Exception {
        userService.removeFromAuctioned(userId, itemId);
    }
}
