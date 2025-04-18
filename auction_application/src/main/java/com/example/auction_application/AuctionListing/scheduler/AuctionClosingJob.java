package com.example.auction_application.AuctionListing.scheduler;

import org.quartz.Job;

import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.auction_application.AuctionListing.services.AuctionListingService;

public class AuctionClosingJob implements Job{

    @Autowired
    AuctionListingService auctionListingService;

    @Override
    public void execute(JobExecutionContext context) {
        Long auctionId = (Long) context.getJobDetail().getJobDataMap().get("auctionCloseId");
        try{
            auctionListingService.closeAuction(auctionId);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
