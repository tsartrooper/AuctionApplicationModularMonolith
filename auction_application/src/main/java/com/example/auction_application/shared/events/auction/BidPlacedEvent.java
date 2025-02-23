package com.example.auction_application.shared.events.auction;

import java.time.LocalDateTime;

import com.example.auction_application.shared.DomainEvent;


public class BidPlacedEvent implements DomainEvent{
    public LocalDateTime occurredOn;
    public Long auctionId;
    public Long bidId;
    public Long bidderId;
    public double amount;

    public BidPlacedEvent(Long auctionId, Long bidId, Long bidderId, double amount){
        System.out.println("building event object");
        this.auctionId = auctionId;
        this.bidId = bidId;
        this.amount = amount;
        this.bidderId = bidderId;
        this.occurredOn = LocalDateTime.now();
    }

    @Override
    public LocalDateTime getOccurredOn(){
        return this.occurredOn;
    }    
}
