package com.example.auction_application.AuctionListing.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.example.auction_application.AuctionListing.Status;
import com.example.auction_application.AuctionListing.entity.AuctionListing;

public class AuctionListingResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private double startingPrice;
    private String description;
    private Long itemId;
    private long duration;
    private Long sellerId;
    private List<Long> bids;
    private long currentHighestBidderId;
    private double currentHighestBid;
    private LocalDateTime endTime;
    private LocalDateTime startTime;
    private Status status;
    private String category;

    public AuctionListingResponseDTO(AuctionListing auctionListing){
        this.id = auctionListing.getId();
        this.startingPrice = auctionListing.getStartingPrice();
        this.description = auctionListing.getDescription();
        this.itemId = auctionListing.getItemId();
        this.duration = auctionListing.getDuration();
        this.sellerId = auctionListing.getSellerId();
        this.endTime = auctionListing.getEndTime();
        this.startTime = auctionListing.getStartTime();
        this.category = auctionListing.getCategory();
        if(auctionListing.getCurrentHighestBidderId() != null){
            this.currentHighestBidderId = auctionListing.getCurrentHighestBidderId();
        }
        this.currentHighestBid = auctionListing.getCurrentHighestBid();
        this.status = auctionListing.getAuctionStatus();
        this.bids = auctionListing.getBids();
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

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public List<Long> getBids() {
        return bids;
    }

    public void setBids(List<Long> bids) {
        this.bids = bids;
    }

    public long getCurrentHighestBidderId() {
        return currentHighestBidderId;
    }

    public void setCurrentHighestBidderId(long currentHighestBidderId) {
        this.currentHighestBidderId = currentHighestBidderId;
    }

    public double getCurrentHighestBid() {
        return currentHighestBid;
    }

    public void setCurrentHighestBid(double currentHighestBid) {
        this.currentHighestBid = currentHighestBid;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
