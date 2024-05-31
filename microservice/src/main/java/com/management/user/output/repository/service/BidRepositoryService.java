package com.management.user.output.repository.service;

import com.management.user.model.Bid;
import com.management.user.output.repository.entity.BidEntity;

import java.util.List;

public interface BidRepositoryService {

    List<BidEntity> findByAuctionId(long auctionId);

    BidEntity save(Bid bid);
}
