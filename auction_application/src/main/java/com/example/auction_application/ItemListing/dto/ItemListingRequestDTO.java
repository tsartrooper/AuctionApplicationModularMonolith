package com.example.auction_application.ItemListing.dto;

public class ItemListingRequestDTO {
    

    String itemName;
    String itemDescription;

    
    double price;
    
    public ItemListingRequestDTO(String itemName, String itemDescription, double price){
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.price = price;
    }

    public ItemListingRequestDTO(){}

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
}
