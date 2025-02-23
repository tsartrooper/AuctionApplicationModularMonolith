package com.example.auction_application.AuctionListing.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.example.auction_application.AuctionListing.AuctionWebSocketHandler;
import com.example.auction_application.AuctionListing.dto.AuctionListingResponseDTO;
import com.example.auction_application.AuctionListing.entity.AuctionListing;
import com.example.auction_application.shared.events.auction.BidPlacedEvent;


@Component
public class AuctionEventHandler {

    @Autowired
    AuctionWebSocketHandler auctionWebSocketHandler;

    @Autowired
    AuctionListingService auctionListingService;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void bidPlacedEventHandler(BidPlacedEvent bidPlacedEvent){

        AuctionListing auctionListing = auctionListingService.getAuctionListingById(bidPlacedEvent.auctionId);

        try{
            auctionWebSocketHandler.broadcastUpdate(new AuctionListingResponseDTO(auctionListing));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }    
}
