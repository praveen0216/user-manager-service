package com.management.user.output.repository.spi;

import com.management.user.model.Status;
import com.management.user.output.repository.entity.AuctionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuctionRepository extends JpaRepository<AuctionEntity, Long>, JpaSpecificationExecutor<AuctionEntity> {
    List<AuctionEntity> findByStatus(Status status);
    List<AuctionEntity> findByAuctioneerId(Long auctioneerId);

    AuctionEntity saveAndFlush(AuctionEntity auctionEntity);

    @Query("SELECT a FROM AuctionEntity a " +
            "JOIN BidEntity b ON a.id = b.auction.id " +
            "WHERE a.id = :auctionId " +
            "GROUP BY a.id " +
            "HAVING MAX(b.bidAmount) = (SELECT MAX(b2.bidAmount) FROM BidEntity b2 WHERE b2.auction.id = :auctionId)")
    Optional<AuctionEntity> findAuctionWithHighestBid(@Param("auctionId") Long auctionId);
}