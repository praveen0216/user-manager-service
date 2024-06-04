package com.management.user.service;
import com.management.user.exception.AuctionNotFoundException;
import com.management.user.exception.InputException;
import com.management.user.exception.ParticipantNotFoundException;
import com.management.user.input.mapper.AuctionMapper;
import com.management.user.input.mapper.BidMapper;
import com.management.user.input.mapper.CycleAvoidingMappingContext;
import com.management.user.model.Auction;
import com.management.user.model.AuctionDetails;
import com.management.user.model.Bid;
import com.management.user.model.Status;
import com.management.user.output.repository.entity.AuctionEntity;
import com.management.user.output.repository.entity.BidEntity;
import com.management.user.output.repository.entity.UserEntity;
import com.management.user.output.repository.service.AuctionRepositoryService;
import com.management.user.output.repository.service.BidRepositoryService;
import com.management.user.output.repository.service.UserRepositoryService;
import com.management.user.output.repository.spi.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AuctionService {

    protected  static final Logger LOGGER = LoggerFactory.getLogger(AuctionService.class);

    private final  AuctionRepositoryService auctionRepositoryService;

    private final BidRepositoryService bidRepositoryService;

    private final AuctionMapper auctionMapper;
    private final BidMapper bidMapper;

    private final UserRepositoryService userRepositoryService;

    private final UpdateAuctionStatusService updateAuctionStatusService;


    @Autowired
    private UserRepository userRepository;

    public AuctionService(AuctionRepositoryService auctionRepositoryService,
                          BidRepositoryService bidRepositoryService,
                          AuctionMapper auctionMapper,
                          BidMapper bidMapper,
                          UserRepositoryService userRepositoryService,
                          UpdateAuctionStatusService updateAuctionStatusService) {
        this.auctionRepositoryService = auctionRepositoryService;
        this.bidRepositoryService = bidRepositoryService;
        this.auctionMapper = auctionMapper;
        this.bidMapper = bidMapper;
        this.userRepositoryService = userRepositoryService;
        this.updateAuctionStatusService = updateAuctionStatusService;
    }

    public Auction createAuction(Auction auction, Long auctioneerId) {
        UserEntity auctioneer = userRepository.findById(auctioneerId).orElseThrow(() -> new RuntimeException("Auctioneer not found"));
        auction.setStatus(Status.ONGOING);
        return auctionMapper.entityToModel(auctionRepositoryService.create(auction, auctioneer),  new CycleAvoidingMappingContext());
    }

    public Bid submitBid(Long auctionId, Long participantId, BigDecimal bidAmount) {
        Auction auction = auctionMapper.entityToModel(auctionRepositoryService.findByAuctionId(auctionId)
                .orElseThrow(() -> new AuctionNotFoundException("Auction not found")) ,  new CycleAvoidingMappingContext());

        UserEntity participantFound = userRepositoryService.findById(participantId).orElseThrow(
                () -> new ParticipantNotFoundException("Participant not found"));
        Bid bidRequest = new Bid();
        bidRequest.setBidAmount(bidAmount);
        bidRequest.setAuctionId(auction.getId());
        bidRequest.setParticipantId(participantFound.getId());

        List<Bid> bidsFromDb = bidMapper.entitiesToModel(bidRepositoryService.findByAuctionId(auctionId));
        validateBid(bidsFromDb, auction, bidRequest);

        bidRequest.setBidTime(LocalDateTime.now());
        return bidMapper.entityToModel(bidRepositoryService.save(bidRequest));
    }

    private void validateBid(List<Bid> bidsFromDb, Auction auction, Bid bidRequest) {
        if (!bidsFromDb.isEmpty() && bidRequest.getBidAmount().compareTo(bidsFromDb.get(0).getBidAmount()) <= 0) {
            throw new InputException("Bid amount must be higher than the current highest bid");
        }
        if (LocalDateTime.now().isBefore(auction.getStartTime()) || LocalDateTime.now().isAfter(auction.getEndTime())) {
            throw new InputException("Bidding is not allowed at this time");
        }
    }

    public AuctionDetails getAuctionDetails(Long auctionId) {
        updateAuctionStatusService.updateAuctionStatus();
        Optional<AuctionEntity> auctionEntity = auctionRepositoryService.findByAuctionId(auctionId);
        if (auctionEntity.isPresent()) {
            List<BidEntity> bidEntities = bidRepositoryService.findByAuctionId(auctionId);
            Bid bid = null;
            if (!bidEntities.isEmpty()) {
              bid = bidMapper.entityToModel(bidEntities.get(0));
            } else {
                LOGGER.info(String.format("Bid not found for the given auction id {} ", auctionId));
            }
            Auction auction = auctionMapper.entityToModel(auctionEntity.get(), new CycleAvoidingMappingContext());
            return new AuctionDetails(auction, bid);
            }
        throw new AuctionNotFoundException(String.format("Auction not found with id {} ", auctionId));
        }

    }
