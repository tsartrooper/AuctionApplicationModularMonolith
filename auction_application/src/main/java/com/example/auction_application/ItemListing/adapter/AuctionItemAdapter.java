package com.example.auction_application.ItemListing.adapter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.auction_application.AuctionListing.port.AuctionItemPort;
import com.example.auction_application.ItemListing.dto.ItemListingResponseDTO;
import com.example.auction_application.ItemListing.services.ItemListingService;


@Component
public class AuctionItemAdapter implements AuctionItemPort{

    @Autowired
    ItemListingService itemListingService;

    @Override
    public boolean userHasItem(Long userId, Long itemId) throws Exception {
        List<ItemListingResponseDTO> items =  itemListingService.findByOwnerId(userId);

        for(ItemListingResponseDTO item : items){
            if(item.getId() == itemId) return true;
        }

        return false;
    }
    
}
