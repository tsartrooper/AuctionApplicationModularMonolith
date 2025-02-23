package com.example.auction_application.AuctionListing.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.auction_application.AuctionListing.AuctionListingRepository;
import com.example.auction_application.AuctionListing.Status;
import com.example.auction_application.AuctionListing.entity.AuctionListing;
import com.example.auction_application.Bid.port.AuctionBiddingPort;

@Component
public class AuctionBiddingAdapter implements AuctionBiddingPort{
    
    @Autowired
    public AuctionListingRepository auctionListingRepository;

    @Override
    public void validateBid(long auctionId, long bidderId,
                                    double amount) throws Exception{
        
        System.out.println("auction id: "+auctionId);
        AuctionListing auctionListing = auctionListingRepository.findById(auctionId);
        if(auctionListing == null){
            throw new Exception("auction with id not found");
        }
        System.out.println("auction id: "+auctionId);

        if(!isAuctionActive(auctionId)){
            throw new Exception("auction is currently not active.");
        }
        
        if(auctionListing.getCurrentHighestBid()>= amount){
            throw new Exception("amount is not greater than current highest bid.");
        } 
    }

    @Override
    public long getCurrentHighestBidderId(long auctionId) throws Exception {
        AuctionListing auctionListing = auctionListingRepository.findById(auctionId);
        if(auctionListing == null){
            throw new Exception("auction with id not found");
        }
        return auctionListing.getId();
        
    }

    @Override
    public boolean isAuctionActive(long auctionId) throws Exception {
        AuctionListing auctionListing = auctionListingRepository.findById(auctionId);

        return auctionListing.getAuctionStatus() == Status.ACTIVE;        
    }

    @Override
    public void updateBid(long auctionId, long bidderId,
            double amount, long bidId) throws Exception{
                AuctionListing auctionListing = auctionListingRepository.findById(auctionId);

                auctionListing.setCurrentHighestBid(amount);
                auctionListing.setCurrentHighestBidderId(bidderId);

                auctionListing.addBid(bidId);

                auctionListingRepository.save(auctionListing);
    }

}
