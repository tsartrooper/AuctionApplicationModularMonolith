package com.example.auction_application.AuctionListing.services;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.auction_application.AuctionListing.AuctionListingRepository;
import com.example.auction_application.AuctionListing.AuctionWebSocketHandler;
import com.example.auction_application.AuctionListing.Status;
import com.example.auction_application.AuctionListing.dto.AuctionListingRequestDTO;
import com.example.auction_application.AuctionListing.dto.AuctionListingResponseDTO;
import com.example.auction_application.AuctionListing.entity.AuctionListing;
import com.example.auction_application.AuctionListing.port.AuctionItemPort;
import com.example.auction_application.AuctionListing.scheduler.AuctionListingSchedulerService;
import com.example.auction_application.shared.ports.AuctionPort;

@Service
public class AuctionListingService {
    @Autowired 
    AuctionListingRepository auctionListingRepository;


    @Autowired
    AuctionListingSchedulerService auctionListingSchedulerService;

    @Autowired
    AuctionWebSocketHandler auctionWebSocketHandler;

    @Autowired
    AuctionPort auctionUserPort;

    @Autowired
    AuctionItemPort auctionItemPort;

    @Transactional
    @CacheEvict(value = {"auctions", "activeAuctions", "closedAuctions", "categoryAuctions", "sellerAuctions", "status", "auction"}, allEntries = true) 
    public boolean createAuctionListing(AuctionListingRequestDTO auctionListingDTO, Long sellerId) throws Exception{
        
        auctionUserPort.userContainsItem(sellerId, auctionListingDTO.getItemId());

        AuctionListing auctionListing = new AuctionListing();

        auctionListing.setStartingPrice(auctionListingDTO.getStartingPrice());
        auctionListing.setDescription(auctionListingDTO.getDescription());
        auctionListing.setDuration(auctionListingDTO.getDuration());
        auctionListing.setSellerId(sellerId);
        auctionListing.setStartTime(LocalDateTime.now().plusMinutes(auctionListingDTO.getStartDelay()));
        auctionListing.setAuctionStatus(Status.SCHEDULED);
        auctionListing.setCurrentHighestBid(auctionListingDTO.getStartingPrice());
        auctionListing.setEndTime(LocalDateTime.now().plusMinutes(auctionListing.getDuration()+auctionListingDTO.getStartDelay()));
        auctionListing.setCategory(auctionListingDTO.getCategory());
        auctionListing.setItemId(auctionListingDTO.getItemId());

        auctionListingRepository.save(auctionListing);
        auctionUserPort.addToSellingAuctions(sellerId, auctionListing.getId());
        auctionUserPort.removeFromOwned(sellerId, auctionListing.getItemId());
        auctionUserPort.addToAuctioned(sellerId , auctionListing.getItemId());

        auctionListingSchedulerService.scheduleAuctionActivation(
                                                auctionListing.getId(),
                                                auctionListing.getStartTime());
        auctionListingSchedulerService.scheduleAuctionClosing(
                                                auctionListing.getId(),
                                                auctionListing.getEndTime());
        

        
        try{
            auctionWebSocketHandler.broadcastUpdate(new AuctionListingResponseDTO(auctionListing));
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return true;
    }

    @Transactional
    @CacheEvict(value = {"auctions", "activeAuctions", "closedAuctions", "categoryAuctions", "sellerAuctions", "status", "auction"}, allEntries = true) 
    public void save(AuctionListing auctionListing){
        auctionListingRepository.save(auctionListing);
    }

    @Cacheable(value = "auctions", key = "'auctions:'+#pageable.pageNumber+#pageable.pageSize")
    public Page<AuctionListingResponseDTO> getAuctionListings(Pageable pageable){
        return auctionListingRepository.findAll(pageable)
                                        .map(AuctionListingResponseDTO::new);
    }

    public AuctionListing getAuctionListingById(Long id){
        Optional<AuctionListing> auctionListing = auctionListingRepository.findById(id);
        if(!auctionListing.isPresent()) return null;
        
        return auctionListing.get();
    }    

    @Cacheable(value = "status", key = "#status + #pageable.pageNumber+#pageable.pageSize")
    public Page<AuctionListing> getByAuctionStatus(Status status, Pageable pageable){
        return auctionListingRepository.findByAuctionStatus(status, pageable);
    }

    @Transactional
    @CacheEvict(value = {"auctions", "activeAuctions", "closedAuctions", "categoryAuctions", "sellerAuctions", "status", "auction"}, allEntries = true) 
    public void deleteAllAuctions(){
        auctionListingRepository.deleteAll();
        
        return;
    }

    @Transactional
    @CacheEvict(value = {"auctions", "activeAuctions", "closedAuctions", "categoryAuctions", "sellerAuctions", "status", "auction"}, allEntries = true) 
    public void closeAuction(Long auctionId) throws Exception{
        AuctionListing auctionListing = auctionListingRepository.findById(auctionId).orElseThrow(() -> new Exception("auction with id does not exist."));
        
        auctionListing.setAuctionStatus(Status.CLOSED);

        auctionListingRepository.save(auctionListing);

        auctionUserPort.addToAuctioned(auctionListing.getCurrentHighestBidderId(), auctionListing.getItemId());
        auctionUserPort.removeFromAuctioned(auctionListing.getSellerId(), auctionListing.getItemId());
        
        return;
    }

    @Transactional
    @CacheEvict(value = {"auctions", "activeAuctions", "closedAuctions", "categoryAuctions", "sellerAuctions", "status", "auction"}, allEntries = true) 
    public void activateAuction(Long auctionId) {
        Optional<AuctionListing> auctionListing = auctionListingRepository.findById(auctionId);
        
        if(!auctionListing.isPresent()) return;
        
        auctionListing.get().setAuctionStatus(Status.ACTIVE);

        auctionListingRepository.save(auctionListing.get());
        try{
            auctionWebSocketHandler.broadcastUpdate(new AuctionListingResponseDTO(auctionListing.get()));
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        return;
    }

    @Cacheable(value = "auction", key="'auction: '+ #id")
    public AuctionListingResponseDTO getAuctionDTOById(Long id){
        Optional<AuctionListing> auctionListing = auctionListingRepository.findById(id);
        if(!auctionListing.isPresent()) return null;
        
        return new AuctionListingResponseDTO(auctionListing.get());
    }

}
