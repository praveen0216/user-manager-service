package com.management.user.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class Auction {
    private Long id;

    @NotEmpty(message = "Title is required")
    private String title;

    private String description;

    @NotNull(message = "Reserved price is required")
    private BigDecimal reservedPrice;

    @NotNull(message = "Start time is required")
    private LocalDateTime startTime;

    @NotNull(message = "End time is required")
    private LocalDateTime endTime;

    private Status status;

    private Long auctioneerId;


}
