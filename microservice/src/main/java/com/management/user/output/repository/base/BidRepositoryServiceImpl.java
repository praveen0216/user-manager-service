package com.management.user.output.repository.base;

import com.management.user.input.mapper.BidMapper;
import com.management.user.model.Bid;
import com.management.user.output.repository.entity.BidEntity;
import com.management.user.output.repository.service.BidRepositoryService;
import com.management.user.output.repository.spi.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BidRepositoryServiceImpl implements BidRepositoryService {

    private final BidRepository bidRepository;
    private final BidMapper bidMapper;

    @Autowired
    public BidRepositoryServiceImpl(BidRepository bidRepository, BidMapper bidMapper) {
        this.bidRepository = bidRepository;
        this.bidMapper = bidMapper;
    }


    @Override
    public List<BidEntity> findByAuctionId(long auctionId) {
        return bidRepository.findByAuctionIdOrderByBidAmountDesc(auctionId);
    }

    @Override
    public BidEntity save(Bid bid) {
        return bidRepository.saveAndFlush(bidMapper.modelToEntity(bid));
    }
}

