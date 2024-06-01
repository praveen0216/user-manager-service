package com.management.user.output.repository.spi;

import com.management.user.model.Status;
import com.management.user.output.repository.entity.AuctionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuctionRepository extends JpaRepository<AuctionEntity, Long>, JpaSpecificationExecutor<AuctionEntity> {
    List<AuctionEntity> findByStatus(Status status);
    List<AuctionEntity> findByAuctioneerId(Long auctioneerId);

    AuctionEntity saveAndFlush(AuctionEntity auctionEntity);

}