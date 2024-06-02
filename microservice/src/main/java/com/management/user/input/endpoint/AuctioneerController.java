package com.management.user.input.endpoint;

import com.management.user.model.Auction;
import com.management.user.model.AuctionDetails;
import com.management.user.model.Status;
import com.management.user.service.AuctionService;
import com.management.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auctioneer")
public class AuctioneerController {

    private final AuctionService auctionService;


    private final UserService userService;

    public AuctioneerController(AuctionService auctionService, UserService userService) {
        this.auctionService = auctionService;
        this.userService = userService;
    }

    @PreAuthorize("hasRole('AUCTIONEER')")
    @PostMapping("/add")
    public ResponseEntity<String> addItem(@RequestBody Auction auction) {
        //Long auctioneerId = userService.findByEmail("auctioneer1@gmail.com"/*getUserDetails().getUsername()*/).getId();
        Long auctioneerId = userService.findByEmail(getUserDetails().getUsername()).getId();
        auction.setStatus(Status.ONGOING);
        auctionService.createAuction(auction, auctioneerId);
        return ResponseEntity.ok("Item added successfully");
    }

    @PreAuthorize("hasRole('AUCTIONEER')")
    @GetMapping("/status/{id}")
    public AuctionDetails getAuctionDetails(@PathVariable("id") Long id) {
        return auctionService.getAuctionDetails(id);
    }

    private UserDetails getUserDetails() {
        return   (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
