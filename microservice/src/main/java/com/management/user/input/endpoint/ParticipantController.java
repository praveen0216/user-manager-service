package com.management.user.input.endpoint;

import com.management.user.service.AuctionService;
import com.management.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/participant")
public class ParticipantController {


    private final AuctionService auctionService;

    private final  UserService userService;

    public ParticipantController(AuctionService auctionService, UserService userService) {
        this.auctionService = auctionService;
        this.userService = userService;
    }

    @PreAuthorize("hasRole('PARTICIPANT')")
    @PostMapping("/{id}/bids")
    public ResponseEntity<String> submitBid(@PathVariable("id") Long auctionId, @RequestParam(name = "bidAmount") BigDecimal bidAmount) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //Long participantId = userService.findByEmail("participant2@gmail.com"/*userDetails.getUsername()*/).getId();
        Long participantId = userService.findByEmail(userDetails.getUsername()).getId();
        auctionService.submitBid(auctionId, participantId, bidAmount);
        return ResponseEntity.ok("Bid submitted successfully");
    }


}
