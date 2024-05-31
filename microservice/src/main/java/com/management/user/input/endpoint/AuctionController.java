package com.management.user.input.endpoint;

import com.management.user.model.Auction;
import com.management.user.model.Bid;
import com.management.user.service.AuctionService;
import com.management.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auctions")
public class AuctionController {

    @Autowired
    private AuctionService auctionService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> createAuction(@RequestBody Auction auction) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long auctioneerId = userService.findByUsername(userDetails.getUsername()).getId();
        auctionService.createAuction(auction, auctioneerId);
        return ResponseEntity.ok("Auction created successfully");
    }

    @PostMapping("/{id}/bids")
    public ResponseEntity<?> submitBid(@PathVariable Long id, @RequestBody Bid bid) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long participantId = userService.findByUsername(userDetails.getUsername()).getId();
        auctionService.submitBid(id, participantId, bid);
        return ResponseEntity.ok("Bid submitted successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Auction> getAuctionDetails(@PathVariable Long id) {
        Auction auction = auctionService.getAuctionDetails(id);
        return ResponseEntity.ok(auction);
    }

    @GetMapping
    public ResponseEntity<List<Auction>> getAllAuctions() {
        List<Auction> auctions = auctionService.getAllAuctions();
        return ResponseEntity.ok(auctions);
    }
}

