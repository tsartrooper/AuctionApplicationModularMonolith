package com.example.auction_application.ItemListing.dto;

import java.io.Serializable;

import com.example.auction_application.ItemListing.entity.ItemListing;


public class ItemListingResponseDTO {
    Long id;
    String itemName;
    String itemDescription;
    double price;
    public ItemListingResponseDTO(Long id, String itemName, String itemDescription, double price) {
        this.id = id;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.price = price;
    }

    public ItemListingResponseDTO(ItemListing itemListing){
        this.id = itemListing.getId();
        this.itemName = itemListing.getName();
        this.itemDescription = itemListing.getDescription();
        this.price = itemListing.getPrice();
    }

    public ItemListingResponseDTO(){}

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    };   
}
