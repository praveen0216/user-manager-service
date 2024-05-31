package com.management.user.output.repository.entity;

import com.management.user.model.Bid;
import com.management.user.model.Status;
import com.management.user.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Setter
@Getter
@Builder
@NoArgsConstructor
@Entity
@Table(name = "tb_auction")
public class AuctionEntity extends CommonEntity {


    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;
    @Column(name = "reservedPrice")
    private BigDecimal reservedPrice;

    @Column(name = "startTime")
    private LocalDateTime startTime;

    @Column(name = "endTime")
    private LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "auctioneer_id")
    private User auctioneer;

    @OneToMany(mappedBy = "auction")
    private List<Bid> bids;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;


}
