package com.example.auction_application.AuctionListing.dto;

public class AuctionListingRequestDTO {
    private Long itemId;
    private double startingPrice;
    private String description;
    private long duration;
    private long startDelay;
    private String category;

    public AuctionListingRequestDTO(Long itemId, double startingPrice, String description, long duration, String category, long startDelay) {
        this.itemId = itemId;
        this.startingPrice = startingPrice;
        this.description = description;
        this.duration = duration;
        this.category = category;
        this.startDelay = startDelay;
    }

    public AuctionListingRequestDTO() {
    }
    
    public double getStartingPrice() {
        return startingPrice;
    }
    public void setStartingPrice(double startingPrice) {
        this.startingPrice = startingPrice;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public long getDuration() {
        return duration;
    }
    
    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getStartDelay() {
        return startDelay;
    }

    public void setStartDelay(long startDelay) {
        this.startDelay = startDelay;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }
}
