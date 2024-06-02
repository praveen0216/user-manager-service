package com.management.user.model;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;


public class Bid {
    private Long id;

    @NotNull(message = "Bid amount is required")
    private BigDecimal bidAmount;

    private LocalDateTime bidTime;


    @NotNull(message = "Auction ID is required")
    private Long auctionId;

    @NotNull(message = "Participant ID is required")
    private Long participantId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(Long auctionId) {
        this.auctionId = auctionId;
    }

    public Long getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Long participantId) {
        this.participantId = participantId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bid bid)) return false;
        return Objects.equals(getId(), bid.getId()) && Objects.equals(getBidAmount(), bid.getBidAmount()) && Objects.equals(getBidTime(), bid.getBidTime()) && Objects.equals(getAuctionId(), bid.getAuctionId()) && Objects.equals(getParticipantId(), bid.getParticipantId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getBidAmount(), getBidTime(), getAuctionId(), getParticipantId());
    }

    @Override
    public String toString() {
        return "Bid{" +
                "id=" + id +
                ", bidAmount=" + bidAmount +
                ", timestamp=" + bidTime +
                ", auctionId=" + auctionId +
                ", participantId=" + participantId +
                '}';
    }
}
