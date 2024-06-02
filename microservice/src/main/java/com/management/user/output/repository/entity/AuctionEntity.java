package com.management.user.output.repository.entity;

import com.management.user.model.Status;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "tb_auction")
@EntityListeners(AuditingEntityListener.class)
public class AuctionEntity extends CommonEntity {


    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;
    @Column(name = "reserved_price", nullable = false)
    private BigDecimal reservedPrice;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auctioneer_id", referencedColumnName = "id", nullable = false)
    private UserEntity auctioneer;

    @OneToMany(mappedBy = "auction", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BidEntity> bids;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getReservedPrice() {
        return reservedPrice;
    }

    public void setReservedPrice(BigDecimal reservedPrice) {
        this.reservedPrice = reservedPrice;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public UserEntity getAuctioneer() {
        return auctioneer;
    }

    public void setAuctioneer(UserEntity auctioneer) {
        this.auctioneer = auctioneer;
    }

    public List<BidEntity> getBids() {
        return bids;
    }

    public void setBids(List<BidEntity> bids) {
        this.bids = bids;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuctionEntity that)) return false;
        return Objects.equals(getTitle(), that.getTitle()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getReservedPrice(), that.getReservedPrice()) && Objects.equals(getStartTime(), that.getStartTime()) && Objects.equals(getEndTime(), that.getEndTime()) && Objects.equals(getAuctioneer(), that.getAuctioneer()) && Objects.equals(getBids(), that.getBids()) && getStatus() == that.getStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getDescription(), getReservedPrice(), getStartTime(), getEndTime(), getAuctioneer(), getBids(), getStatus());
    }

    @Override
    public String toString() {
        return "AuctionEntity{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", reservedPrice=" + reservedPrice +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", auctioneer=" + auctioneer +
                ", bids=" + bids +
                ", status=" + status +
                '}';
    }
}
