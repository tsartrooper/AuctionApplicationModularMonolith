package com.example.auction_application.Bid.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Bid {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private double amount;
    private LocalDateTime timeStamp;
    private Long auctionListingId;
    private Long bidderId;

    public Bid(){}

    public Bid(Long auctionListingId, Long bidderId, double amount){
        this.amount = amount;
        this.auctionListingId = auctionListingId;
        this.bidderId = bidderId;
        this.timeStamp = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Long getBidderId() {
        return bidderId;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }    

    public void setId(Long id) {
        this.id = id;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setAuctionListingId(Long auctionListingId) {
        this.auctionListingId = auctionListingId;
    }

    public void setBidderId(Long bidderId) {
        this.bidderId = bidderId;
    }

    public Long getAuctionListingId() {
        return auctionListingId;
    }    
}
