package com.management.user.output.repository.spi;


import com.management.user.output.repository.entity.BidEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BidRepository extends JpaRepository<BidEntity, Long> {
    List<BidEntity> findByAuctionIdOrderByBidAmountDesc(Long auctionId);
    List<BidEntity> findByParticipantId(Long participantId);
}