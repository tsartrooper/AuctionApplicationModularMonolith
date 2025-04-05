package com.example.auction_application.Bid.service;

import java.time.LocalDateTime;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.auction_application.Bid.BidRepository;
import com.example.auction_application.Bid.dto.BidRequestDTO;
import com.example.auction_application.Bid.entity.Bid;
import com.example.auction_application.Bid.port.AuctionBiddingPort;
import com.example.auction_application.Bid.port.UserBiddingPort;
import com.example.auction_application.UserModule.UserRepository;
import com.example.auction_application.shared.DomainEvents;
import com.example.auction_application.shared.events.auction.BidPlacedEvent;

@Service
public class BidService {

    @Autowired
    BidRepository bidRepository;

    @Autowired
    UserBiddingPort userBiddingPort;

    @Autowired
    AuctionBiddingPort auctionBiddingPort;

    @Transactional
    @CacheEvict(value = {"auctions", "activeAuctions", "closedAuctions", "categoryAuctions", "sellerAuctions", "status", "auction"}, allEntries = true)
    public void createBid(BidRequestDTO bidDTO) throws Exception{
        if(true) throw new Exception("an exception");

        if(bidDTO.getAmount()<=0 || bidDTO.getAuctionListingId() <= 0 || bidDTO.getBidderId() <= 0){
            throw new Exception("amount or ids cannot be negative.");
        }
        
        auctionBiddingPort.validateBid(bidDTO.getAuctionListingId(), bidDTO.getBidderId(), bidDTO.getAmount());
        userBiddingPort.userWithIdExists(bidDTO.getBidderId());

        Bid bid = new Bid();

        bid.setAmount(bidDTO.getAmount());
        bid.setAuctionListingId(bidDTO.getAuctionListingId());
        bid.setBidderId(bidDTO.getBidderId());
        bid.setTimeStamp(LocalDateTime.now());

        bidRepository.save(bid);
        auctionBiddingPort.updateBid(bidDTO.getAuctionListingId(), bidDTO.getBidderId(), bidDTO.getAmount(), bid.getId());
        userBiddingPort.addBid(bidDTO.getBidderId(), bid.getId());

        DomainEvents.raise(new BidPlacedEvent(
                        bidDTO.getAuctionListingId(), bid.getId(),
                        bidDTO.getBidderId(), bidDTO.getAmount()));
    }

    public List<Bid> getAllBids(){
        return bidRepository.findAll();
    }

    public List<Bid> getBidsByBidderId(Long bidderID){
        return bidRepository.findByBidderId(bidderID);
    }
    
}
