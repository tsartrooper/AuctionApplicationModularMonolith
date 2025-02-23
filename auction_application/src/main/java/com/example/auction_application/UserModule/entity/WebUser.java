package com.example.auction_application.UserModule.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class WebUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String userName;

    @Column(unique = true, nullable = false)
    private String userEmail;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    private List<Long> ownedItems = new ArrayList<>();

    private List<Long> auctionedItems = new ArrayList<>();

    private List<Long> bids = new ArrayList<>();

    private List<Long> sellingAuctions = new ArrayList<>();

    private List<Long> biddingAuctions = new ArrayList<>();

    public WebUser() {
    }

    public WebUser(String userName, String userEmail, String password, String role) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserEmail() {
        return userEmail;
    }
    public List<Long> getOwnedItems() {
        return ownedItems;
    }

    public void setOwnedItems(List<Long> ownedItems) {
        this.ownedItems = ownedItems;
    }

    public List<Long> getAuctionedItems() {
        return auctionedItems;
    }

    public void setAuctionedItems(List<Long> auctionedItems) {
        this.auctionedItems = auctionedItems;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Long> getBids() {
        return bids;
    }

    public void setBids(List<Long> bids) {
        this.bids = bids;
    }

    public List<Long> getSellingAuctions() {
        return sellingAuctions;
    }

    public void setSellingAuctions(List<Long> sellingAuctions) {
        this.sellingAuctions = sellingAuctions;
    }

    public List<Long> getBiddingAuctions() {
        return biddingAuctions;
    }

    public void setBiddingAuctions(List<Long> biddingAuctions) {
        this.biddingAuctions = biddingAuctions;
    }
}
