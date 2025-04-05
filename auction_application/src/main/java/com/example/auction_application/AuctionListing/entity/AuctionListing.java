package com.example.auction_application.AuctionListing.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.auction_application.AuctionListing.Status;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class AuctionListing {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private double startingPrice;

    @Column(nullable = false)
    private String description;

    @Column(unique = true, nullable = false)
    private Long itemId;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private long duration;
    
    private double currentHighestBid;
    private LocalDateTime endTime;
    private String category;

    @Enumerated(EnumType.STRING)
    private Status auctionStatus; 

    private Long currentHighestBidderId;

    private List<Long> bids = new ArrayList<>();
    
    private Long sellerId;    

    public AuctionListing() {
    }
    public AuctionListing(double startingPrice, String description, Long itemId,
            long duration, Long sellerId, String category, long startDelay) {
        this.startingPrice = startingPrice;
        this.description = description;
        this.itemId = itemId;
        this.duration = duration;
        this.currentHighestBid = startingPrice;
        this.sellerId = sellerId;
        this.auctionStatus = Status.ACTIVE;
        this.currentHighestBidderId = sellerId;
        this.endTime = LocalDateTime.now().plusMinutes(duration+startDelay);
        this.category = category;
        this.startTime = LocalDateTime.now().plusMinutes(startDelay);
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
    public Long getItemId() {
        return itemId;
    }
    public long getDuration() {
        return duration;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public double getCurrentHighestBid() {
        return currentHighestBid;
    }
    public void setCurrentHighestBid(double currentHighestBid) {
        this.currentHighestBid = currentHighestBid;
    }
    public Long getCurrentHighestBidderId() {
        return currentHighestBidderId;
    }
    public void setCurrentHighestBidderId(Long currentHighestBidderId) {
        this.currentHighestBidderId = currentHighestBidderId;
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
    public Status getAuctionStatus() {
        return auctionStatus;
    }
    public void setAuctionStatus(Status auctionStatus) {
        this.auctionStatus = auctionStatus;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }
    public void setDuration(long duration) {
        this.duration = duration;
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
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void addBid(Long bidId) {
        this.bids.add(bidId);
    }

}
