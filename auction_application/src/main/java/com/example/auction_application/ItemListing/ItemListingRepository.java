package com.example.auction_application.ItemListing;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.auction_application.ItemListing.entity.ItemListing;




public interface ItemListingRepository extends JpaRepository<ItemListing, Long> {
    
    public List<ItemListing> findByOwnerId(Long id);
    
}
