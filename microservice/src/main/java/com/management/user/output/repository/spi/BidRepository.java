package com.management.user.output.repository.spi;

import com.management.user.model.Bid;
import com.management.user.output.repository.entity.BidEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BidRepository extends JpaRepository<BidEntity, Long> {
    List<BidEntity> findByAuctionIdOrderByBidAmountDesc(Long auctionId);
    List<BidEntity> findByParticipantId(Long participantId);
}