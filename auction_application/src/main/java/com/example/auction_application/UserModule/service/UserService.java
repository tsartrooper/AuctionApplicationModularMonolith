package com.example.auction_application.UserModule.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.auction_application.UserModule.UserRepository;
import com.example.auction_application.UserModule.dto.UserRequestDTO;

import com.example.auction_application.UserModule.entity.WebUser;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Transactional
    public String createUser(UserRequestDTO userRequestDTO){
        try {
            if(userRepository.findByUserName(userRequestDTO.getUserName()).isPresent()) return "user already exists.";
            userRepository.save(userRequestDTO.getWebUser());
            return "successfully created a user";   
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Transactional
    public void save(WebUser webUser){
        userRepository.save(webUser);
    }

    public List<WebUser> getUsers(){
        return userRepository.findAll();
    }

    public List<Long> getBidsByUserId(Long user_id){
        Optional<WebUser> webUser = userRepository.findById(user_id);
        if(webUser.isPresent()){
            return webUser.get().getBids();
        }
        return null;
    }    

    @Transactional
    public void deleteAllUsers(){
        userRepository.deleteAll();
    }

    @Transactional
    public String saveReqDTO(UserRequestDTO userRequestDTO) {        
        return createUser(userRequestDTO);
    }

    public WebUser findById(Long Id) throws Exception{
        return userRepository.findById(Id)
                    .orElseThrow(() -> new Exception("user with id does not exist."));
    }

    public WebUser findByUserName(String userName) {
        return userRepository.findByUserName(userName).orElse(null);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addToOwned(Long userId, Long itemId) throws Exception{
        WebUser user = findById(userId);

        List<Long> ownedItems = user.getOwnedItems();

        ownedItems.add(itemId);

        user.setOwnedItems(ownedItems);
        save(user);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void removeFromOwned(Long userId, Long itemId) throws Exception{
        WebUser user = findById(userId);

        List<Long> ownedItems = user.getOwnedItems();

        if(!ownedItems.contains(itemId)) throw new Exception("user does not own item with id.");

        ownedItems.remove(itemId);

        user.setOwnedItems(ownedItems);
        save(user);        
    }
    
    @Transactional(propagation = Propagation.REQUIRED)
    public void addToAuctioned(Long userId, Long itemId) throws Exception{
        WebUser user = findById(userId);

        List<Long> auctionedItems = user.getAuctionedItems();

        auctionedItems.add(itemId);

        user.setAuctionedItems(auctionedItems);
        save(user); 
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void removeFromAuctioned(Long userId, Long itemId) throws Exception{
        WebUser user = findById(userId);

        List<Long> auctionedItems = user.getAuctionedItems();

        auctionedItems.remove(itemId);

        user.setAuctionedItems(auctionedItems);

        save(user);
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public void addSellingAuctionId(Long userId, Long auctionId) throws Exception{
        WebUser webUser = userRepository.findById(userId).orElseThrow(() -> new Exception("user with id does not exist."));
        
        List<Long> sellingAuctions = webUser.getSellingAuctions();

        sellingAuctions.add(auctionId);

        webUser.setSellingAuctions(sellingAuctions);

        userRepository.save(webUser);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addBid(Long userId, Long bidId) throws Exception{
        WebUser webUser = userRepository.findById(userId)
                .orElseThrow(() -> 
                        new Exception("user with id does not exist."));
        
        List<Long> bids = webUser.getBids();
        bids.add(bidId);
        userRepository.save(webUser);
    }


}
