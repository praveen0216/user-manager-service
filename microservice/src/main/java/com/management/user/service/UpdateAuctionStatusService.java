package com.management.user.service;

import com.management.user.input.mapper.AuctionMapper;
import com.management.user.input.mapper.BidMapper;
import com.management.user.input.mapper.CycleAvoidingMappingContext;
import com.management.user.model.Auction;
import com.management.user.model.Bid;
import com.management.user.model.Status;
import com.management.user.output.repository.service.AuctionRepositoryService;
import com.management.user.output.repository.service.BidRepositoryService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class UpdateAuctionStatusService {

    private final AuctionRepositoryService auctionRepositoryService;

    private final BidRepositoryService bidRepositoryService;
    private final AuctionMapper auctionMapper;
    private final BidMapper bidMapper;

    public UpdateAuctionStatusService(AuctionRepositoryService auctionRepositoryService, BidRepositoryService bidRepositoryService, AuctionMapper auctionMapper, BidMapper bidMapper) {
        this.auctionRepositoryService = auctionRepositoryService;
        this.bidRepositoryService = bidRepositoryService;
        this.auctionMapper = auctionMapper;
        this.bidMapper = bidMapper;
    }

    public  void updateAuctionStatus() {
        List<Auction> ongoingAuctions = auctionMapper.entitiesToModels(auctionRepositoryService.findByStatus(Status.ONGOING),
                new CycleAvoidingMappingContext());
        for (Auction auction : ongoingAuctions) {
            if (LocalDateTime.now().isAfter(auction.getEndTime())) {
                List<Bid> bids = bidMapper.entitiesToModel(bidRepositoryService.findByAuctionId(auction.getId()));
                if ((!bids.isEmpty()) && bids.get(0).getBidAmount().compareTo(auction.getReservedPrice()) >= 0) {
                    auction.setStatus(Status.SUCCESS);
                } else {
                    auction.setStatus(Status.FAILURE);
                }
                auctionRepositoryService.updateStatus(auction);
            }
        }
    }
}
