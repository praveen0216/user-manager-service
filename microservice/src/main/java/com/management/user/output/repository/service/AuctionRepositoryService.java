package com.management.user.output.repository.service;

import com.management.user.model.Auction;
import com.management.user.model.Status;
import com.management.user.output.repository.entity.AuctionEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface AuctionRepositoryService {

    Optional<AuctionEntity>  findByAuctionId(Long auctionId);

    AuctionEntity create(Auction auction);

    List<AuctionEntity> findAll();

    List<AuctionEntity> findByStatus(Status status);

    AuctionEntity updateStatus(Auction auction);
}
