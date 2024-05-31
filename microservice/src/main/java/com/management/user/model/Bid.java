package com.management.user.model;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@Builder
public class Bid {
    private Long id;

    @NotNull(message = "Bid amount is required")
    private BigDecimal bidAmount;

    private LocalDateTime timestamp;


    @NotNull(message = "Auction ID is required")
    private Long auctionId;

    @NotNull(message = "Participant ID is required")
    private Long participantId;
}
