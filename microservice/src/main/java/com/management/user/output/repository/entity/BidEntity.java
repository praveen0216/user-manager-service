package com.management.user.output.repository.entity;

import com.management.user.model.Auction;
import com.management.user.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;


@Entity
@Table(name = "tb_bid")
public class BidEntity extends CommonEntity {

    @ManyToOne
    @JoinColumn(name = "auction_id")
    private AuctionEntity auction;

    @ManyToOne
    @JoinColumn(name = "participant_id")
    private UserEntity participant;

    @Column(name = "bidAmount")
    private BigDecimal bidAmount;

    @Column(name = "currentTime")
    private LocalDateTime currentTime;

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

    public LocalDateTime getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(LocalDateTime currentTime) {
        this.currentTime = currentTime;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BidEntity bidEntity)) return false;
        return Objects.equals(getAuction(), bidEntity.getAuction()) && Objects.equals(getParticipant(), bidEntity.getParticipant()) && Objects.equals(getBidAmount(), bidEntity.getBidAmount()) && Objects.equals(getCurrentTime(), bidEntity.getCurrentTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAuction(), getParticipant(), getBidAmount(), getCurrentTime());
    }

    @Override
    public String toString() {
        return "BidEntity{" +
                "auction=" + auction +
                ", participant=" + participant +
                ", bidAmount=" + bidAmount +
                ", currentTime=" + currentTime +
                '}';
    }
}