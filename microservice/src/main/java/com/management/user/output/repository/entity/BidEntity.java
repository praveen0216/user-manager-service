package com.management.user.output.repository.entity;

import com.management.user.model.Auction;
import com.management.user.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;


@Getter
@Setter
@Builder
@NoArgsConstructor
@Entity
@Table(name = "tb_bid")
public class BidEntity extends CommonEntity {

    @ManyToOne
    @JoinColumn(name = "auction_id")
    private Auction auction;

    @ManyToOne
    @JoinColumn(name = "participant_id")
    private User participant;

    @Column(name = "bidAmount")
    private BigDecimal bidAmount;

    @Column(name = "currentTime")
    private LocalDateTime currentTime;




}