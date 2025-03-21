Online Auction Application

Background
Online auction sites are websites that facilitate buying and selling goods via a bidding process. This a backend system for such online auctions. This project makes use of Springboot for managing apis and database connections. 

Goals :
•	Providing REST APIs for User Authentication and Auction Management
•	Scheduling Auctions.
•	Caching API requests.

Non Goals: what it will not cover
•	Frontend implementation and payment gateway integration.
•	Testing of the functionalties.

Architecture Overview:

High Level Design:
This is project particularly follows modular monolith architecture practices. The individual modules are loosely coupled for potential of this project breaking down into microservices down the line.
 ![alt text](https://github.com/tsartrooper/AuctionApplicationModularMonolith/blob/main/images/auction_application_hld.png)
Authentication(Spring Auth)
JWT based authentication Spring Auth was used.

Caching(Redis) 
For Caching Redis a was used.

Scheduling(Spring Quartz Scheduler)
For Scheduling Spring Quartz scheduler was used.

Data Storage(Postgres)
For Database Postgres was used.
Port-Adapter Design pattern is implemented, to maintain interdependency between modules as loosely coupled as possible, this typically was inspired by modular monolilth architecture.

API Overview

1.	Authentication 
•	Post auth/register: Register a new user.
•	Post Auth/ login: User login returns JSON Web Token

2.	Auction Catalog
•	Get /catalog/all get all the recent aauctions.
•	Get /catalog/active get all the active auctions.
•	Get /catalog/category/{category} all auctions of a specific category.
•	Catalog/seller/{seller_id} get all auction of a particular seller.

3.	Auction Management
•	Post  /auctions create an auction.
•	Get /auctions get all the auctions
•	Get auctions/{auction_id} get auction by id

4.	Bids management
•	POST  /bids create a bid
•	GET /bids get all bids 
•	GET /bids/{bidder_id} get bids by bidder id

5.	Item Listing
•	POST /items/create create an item
•	POST /items/owned get all the items owned by the user.

6.	User Module
•	GET /users get all the users/
•	GET /user/{user_id}  get user information by user_id





	
