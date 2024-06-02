package com.management.user.output.repository.spi;

import com.management.user.model.UserFrequency;
import com.management.user.output.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);

    @Query("SELECT new com.management.user.model.UserFrequency(u.email, COUNT(b.id)) " +
            "FROM UserEntity u LEFT JOIN BidEntity b ON u.id = b.participant.id " +
            "WHERE u.role = 'PARTICIPANT' " +
            "GROUP BY u.email")
    List<UserFrequency> findUserBidCounts();

    @Query("SELECT new com.management.user.model.UserFrequency(u.email, COUNT(a.id)) " +
            "FROM UserEntity u LEFT JOIN AuctionEntity a ON u.id = a.auctioneer.id " +
            "WHERE u.role = 'AUCTIONEER' " +
            "GROUP BY u.email")
    List<UserFrequency> findUserAuctionCounts();

}