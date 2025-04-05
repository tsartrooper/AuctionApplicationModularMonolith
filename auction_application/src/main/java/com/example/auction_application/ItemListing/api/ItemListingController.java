package com.example.auction_application.ItemListing.api;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.auction_application.ItemListing.dto.ItemListingRequestDTO;
import com.example.auction_application.ItemListing.dto.ItemListingResponseDTO;
import com.example.auction_application.ItemListing.services.ItemListingService;
import com.example.auction_application.shared.ports.AuthenticationPort;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/items")
public class ItemListingController {

    @Autowired 
    ItemListingService itemListingService;

    @Autowired
    AuthenticationPort authenticationPort;

    @PostMapping("/create")
    @CacheEvict(value = {"auctions", "activeAuctions", "closedAuctions", "categoryAuctions", "sellerAuctions", "status", "auction"}, allEntries = true) 
    public void createItem(HttpServletRequest request, @RequestBody ItemListingRequestDTO itemListingRequestDTO) throws Exception{
        String token = request.getHeader("Authorization");

        Long sellerId = authenticationPort.getUserId(token.substring(7));        

        itemListingService.createItemListing(itemListingRequestDTO, sellerId);
    }
    
    @GetMapping("/owned")
    public List<ItemListingResponseDTO> getOwnedItems(HttpServletRequest request, ItemListingRequestDTO itemListingRequestDTO) throws Exception{
        String token = request.getHeader("Authorization");

        Long sellerId = authenticationPort.getUserId(token.substring(7));  

        return itemListingService.findByOwnerId(sellerId);
    }
    
}
