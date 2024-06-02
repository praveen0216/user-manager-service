package com.management.user.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Auction {
    private Long id;

    @NotEmpty(message = "Title is required")
    private String title;

    private String description;

    @NotNull(message = "Reserved price is required")
    private BigDecimal reservedPrice;

    @NotNull(message = "Start time is required")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", shape = JsonFormat.Shape.STRING)
    private LocalDateTime startTime;

    @NotNull(message = "End time is required")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", shape = JsonFormat.Shape.STRING)
    private LocalDateTime endTime;

    private Status status;

    private Long auctioneerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getAuctioneerId() {
        return auctioneerId;
    }

    public void setAuctioneerId(Long auctioneerId) {
        this.auctioneerId = auctioneerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Auction auction)) return false;
        return Objects.equals(getId(), auction.getId()) && Objects.equals(getTitle(), auction.getTitle()) && Objects.equals(getDescription(), auction.getDescription()) && Objects.equals(getReservedPrice(), auction.getReservedPrice()) && Objects.equals(getStartTime(), auction.getStartTime()) && Objects.equals(getEndTime(), auction.getEndTime()) && getStatus() == auction.getStatus() && Objects.equals(getAuctioneerId(), auction.getAuctioneerId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getDescription(), getReservedPrice(), getStartTime(), getEndTime(), getStatus(), getAuctioneerId());
    }

    @Override
    public String toString() {
        return "Auction{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", reservedPrice=" + reservedPrice +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", status=" + status +
                ", auctioneerId=" + auctioneerId +
                '}';
    }
}
