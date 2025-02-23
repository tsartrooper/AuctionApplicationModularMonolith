package com.example.auction_application.ItemListing.entity;

import org.hibernate.annotations.Collate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ItemListing {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @Column(nullable = false)
    String name;
    String description;
    Long ownerId;
    double price;
    

    public ItemListing(String name, String description, Long ownerId, double price){
        this.name = name;
        this.description = description;
        this.ownerId = ownerId;
        this.price = price;
    }

    public ItemListing(){}

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public Long getOwnerId() {
        return ownerId;
    }


    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }


    public double getPrice() {
        return price;
    }


    public void setPrice(double price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }    
}
