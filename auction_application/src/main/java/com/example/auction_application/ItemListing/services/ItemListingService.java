package com.example.auction_application.ItemListing.services;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.auction_application.ItemListing.ItemListingRepository;
import com.example.auction_application.ItemListing.dto.ItemListingRequestDTO;
import com.example.auction_application.ItemListing.dto.ItemListingResponseDTO;
import com.example.auction_application.ItemListing.entity.ItemListing;
import com.example.auction_application.shared.ports.AuctionPort;

import jakarta.transaction.Transactional;

@Service
public class ItemListingService {

    @Autowired
    ItemListingRepository itemListingRepository;

    @Autowired
    AuctionPort auctionPort;


    public ItemListing findById(Long id) throws Exception{
        return itemListingRepository.findById(id).orElseThrow(() -> new Exception("item with id does not exist"));
    }

    @Transactional
    public void createItemListing(ItemListingRequestDTO itemListingRequestDTO, Long sellerId) throws Exception{
        ItemListing itemListing = new ItemListing(itemListingRequestDTO.getItemName(), itemListingRequestDTO.getItemDescription(),
                                    sellerId, itemListingRequestDTO.getPrice());
        
        save(itemListing);
        auctionPort.addToOwned(sellerId, itemListing.getId());
    }

    public void save(ItemListing itemListing){
        itemListingRepository.save(itemListing);
    }

    public List<ItemListingResponseDTO> findByOwnerId(Long id) throws Exception{
        return itemListingRepository.findByOwnerId(id).stream().map(ItemListingResponseDTO::new).collect(Collectors.toList());
    }    
}
