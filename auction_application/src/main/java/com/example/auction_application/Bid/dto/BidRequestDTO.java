package com.example.auction_application.Bid.dto;

import java.io.Serializable;
import java.time.LocalDateTime;


public class BidRequestDTO implements Serializable{
    private static final long serialVersionUID = 1L;
    private double amount;
    private LocalDateTime timeStamp;
    private long bidderId;
    private long auctionListingId;

    public BidRequestDTO(double amount, long bidderId, long auctionListingId){
        this.amount = amount;
        this.timeStamp = LocalDateTime.now();
        this.bidderId = bidderId;
        this.auctionListingId = auctionListingId;
    }
    public BidRequestDTO(){
        this.timeStamp = LocalDateTime.now();
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public long getBidderId() {
        return bidderId;
    }

    public void setBidderId(long bidderId) {
        this.bidderId = bidderId;
    }

    public long getAuctionListingId() {
        return auctionListingId;
    }

    public void setAuctionListingId(long auctionListingId) {
        this.auctionListingId = auctionListingId;
    }
}
