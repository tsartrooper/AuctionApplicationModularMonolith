package com.example.auction_application;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.example.auction_application.AuctionListing.services.AuctionListingService;
import com.example.auction_application.Bid.dto.BidRequestDTO;
import com.example.auction_application.Bid.service.BidService;

@SpringBootTest
class AuctionApplicationTests {

	@MockitoBean 
	BidService bidService;

	@Test
	void contextLoads() {
	}

	@Test
	void testPlaceBid(){
		BidRequestDTO bidRequestDTO = new BidRequestDTO();
		bidRequestDTO.setAmount(-1);
        bidRequestDTO.setAuctionListingId(-1);
        bidRequestDTO.setBidderId(-1);
		System.out.println("are we getting here");

		assertThrows(Exception.class, () -> {
			bidService.createBid(bidRequestDTO);
		});
	}

}
