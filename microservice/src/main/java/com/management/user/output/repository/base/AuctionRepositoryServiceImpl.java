package com.management.user.output.repository.base;

import com.management.user.exception.AuctionNotFoundException;
import com.management.user.input.mapper.AuctionMapper;
import com.management.user.model.Auction;
import com.management.user.model.Status;
import com.management.user.output.repository.entity.AuctionEntity;
import com.management.user.output.repository.service.AuctionRepositoryService;
import com.management.user.output.repository.spi.AuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AuctionRepositoryServiceImpl implements AuctionRepositoryService {

    private final AuctionRepository auctionRepository;
    private final AuctionMapper auctionMapper;

    @Autowired
    public AuctionRepositoryServiceImpl(AuctionRepository auctionRepository, AuctionMapper auctionMapper) {
        this.auctionRepository = auctionRepository;
        this.auctionMapper = auctionMapper;
    }

    @Override
    public Optional<AuctionEntity> findByAuctionId(Long auctionId) {
        return auctionRepository.findById(auctionId);
    }

    @Override
    public AuctionEntity create(Auction auction) {
        return auctionRepository.saveAndFlush(auctionMapper.modelToEntity(auction));
    }

    @Override
    public List<AuctionEntity> findAll() {
        return auctionRepository.findAll();
    }

    @Override
    public List<AuctionEntity> findByStatus(Status status) {
        return auctionRepository.findByStatus(status);
    }

    @Override
    public AuctionEntity updateStatus(Auction auction) {
        Optional<AuctionEntity> fromDb = auctionRepository.findById(auction.getId());
        if (!fromDb.isPresent()) {
            throw new AuctionNotFoundException(String.format("No auction found with the id {} ",  auction.getId()));
        }
        AuctionEntity updateAuction = fromDb.get();
        updateAuction.setStatus(auction.getStatus());

        return auctionRepository.saveAndFlush(updateAuction);

    }
}
