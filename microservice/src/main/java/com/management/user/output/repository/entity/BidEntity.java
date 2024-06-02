package com.management.user.output.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;


@Entity
@Table(name = "tb_bid")
@EntityListeners(AuditingEntityListener.class)
public class BidEntity extends CommonEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auction_id", referencedColumnName = "id", nullable = false)
    private AuctionEntity auction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participant_id", referencedColumnName = "id", nullable = false)
    private UserEntity participant;

    @Column(name = "bid_amount", nullable = false)
    private BigDecimal bidAmount;

    @Column(name = "bid_time", nullable = false)
    private LocalDateTime bidTime;

    public AuctionEntity getAuction() {
        return auction;
    }

    public void setAuction(AuctionEntity auction) {
        this.auction = auction;
    }

    public UserEntity getParticipant() {
        return participant;
    }

    public void setParticipant(UserEntity participant) {
        this.participant = participant;
    }

    public BigDecimal getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(BigDecimal bidAmount) {
        this.bidAmount = bidAmount;
    }

    public LocalDateTime getBidTime() {
        return bidTime;
    }

    public void setBidTime(LocalDateTime bidTime) {
        this.bidTime = bidTime;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BidEntity bidEntity)) return false;
        return Objects.equals(getAuction(), bidEntity.getAuction()) && Objects.equals(getParticipant(), bidEntity.getParticipant()) && Objects.equals(getBidAmount(), bidEntity.getBidAmount()) && Objects.equals(getBidTime(), bidEntity.getBidTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAuction(), getParticipant(), getBidAmount(), getBidTime());
    }

    @Override
    public String toString() {
        return "BidEntity{" +
                "auction=" + auction +
                ", participant=" + participant +
                ", bidAmount=" + bidAmount +
                ", currentTime=" + bidTime +
                '}';
    }
}