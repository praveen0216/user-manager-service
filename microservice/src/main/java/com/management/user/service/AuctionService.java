package com.management.user.service;
import com.management.user.exception.AuctionNotFoundException;
import com.management.user.input.mapper.AuctionMapper;
import com.management.user.input.mapper.BidMapper;
import com.management.user.model.Auction;
import com.management.user.model.Bid;
import com.management.user.model.Status;
import com.management.user.output.repository.service.AuctionRepositoryService;
import com.management.user.output.repository.service.BidRepositoryService;
import com.management.user.output.repository.service.UserRepositoryService;
import com.management.user.output.repository.spi.AuctionRepository;
import com.management.user.output.repository.spi.BidRepository;
import com.management.user.output.repository.spi.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuctionService {
    @Autowired
    private AuctionRepository auctionRepository;

    private final  AuctionRepositoryService auctionRepositoryService;

    private final BidRepositoryService bidRepositoryService;

    private final AuctionMapper auctionMapper;
    private final BidMapper bidMapper;

    private final UserRepositoryService userRepositoryService;

    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private UserRepository userRepository;

    public AuctionService(AuctionRepositoryService auctionRepositoryService, BidRepositoryService bidRepositoryService, AuctionMapper auctionMapper, BidMapper bidMapper, UserRepositoryService userRepositoryService) {
        this.auctionRepositoryService = auctionRepositoryService;
        this.bidRepositoryService = bidRepositoryService;
        this.auctionMapper = auctionMapper;
        this.bidMapper = bidMapper;
        this.userRepositoryService = userRepositoryService;
    }

    public Auction createAuction(Auction auction, Long auctioneerId) {
        auction.setAuctioneer(userRepository.findById(auctioneerId).orElseThrow(() -> new RuntimeException("Auctioneer not found")));
        auction.setStatus(Status.ONGOING);
        return auctionMapper.entityToModel(auctionRepositoryService.create(auction));
    }

    public Bid submitBid(Long auctionId, Long participantId, Bid bid) {
        Auction auction = auctionMapper.entityToModel(auctionRepositoryService.findByAuctionId(auctionId)
                .orElseThrow(() -> new RuntimeException("Auction not found")));
        bid.setAuctionId(auction.getId());
        bid.setParticipantId(userRepositoryService.findById(participantId).orElseThrow(() -> new RuntimeException("Participant not found")));

        List<Bid> bids = bidMapper.entitiesToModel(bidRepositoryService.findByAuctionId(auctionId));
        if (!bids.isEmpty() && bid.getBidAmount().compareTo(bids.get(0).getBidAmount()) <= 0) {
            throw new RuntimeException("Bid amount must be higher than the current highest bid");
        }
        if (LocalDateTime.now().isBefore(auction.getStartTime()) || LocalDateTime.now().isAfter(auction.getEndTime())) {
            throw new RuntimeException("Bidding is not allowed at this time");
        }
        bid.setTimestamp(LocalDateTime.now());
        return bidMapper.entityToModel(bidRepositoryService.save(bid));
    }

    public Auction getAuctionDetails(Long auctionId) {
        return auctionMapper.entityToModel(auctionRepositoryService.findByAuctionId(auctionId)
                .orElseThrow(() -> new AuctionNotFoundException(String.format("Auction not found with id {}", auctionId)));
    }

    public List<Auction> getAllAuctions() {
        return auctionMapper.entitiesToModels(auctionRepositoryService.findAll());
    }

    public void updateAuctionStatus() {
        List<Auction> ongoingAuctions = auctionMapper.entitiesToModels(auctionRepositoryService.findByStatus(Status.ONGOING));
        for (Auction auction : ongoingAuctions) {
            if (LocalDateTime.now().isAfter(auction.getEndTime())) {
                List<Bid> bids = bidRepositoryService.findByAuctionId(auction.getId());
                if (!bids.isEmpty() && bids.get(0).getBidAmount().compareTo(auction.getReservedPrice()) >= 0) {
                    auction.setStatus(Status.SUCCESS);
                } else {
                    auction.setStatus(Status.FAILURE);
                }
                auctionRepositoryService.updateStatus(auction);
            }
        }
    }
}
