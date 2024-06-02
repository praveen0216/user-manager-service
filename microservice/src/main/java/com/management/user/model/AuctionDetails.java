package com.management.user.model;

import java.util.Objects;

public class AuctionDetails {

    private Auction auction;

    private Bid bid;


    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    public Bid getBid() {
        return bid;
    }

    public void setBid(Bid bid) {
        this.bid = bid;
    }

    public AuctionDetails(Auction auction, Bid bid) {
        this.auction = auction;
        this.bid = bid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuctionDetails that)) return false;
        return Objects.equals(getAuction(), that.getAuction()) && Objects.equals(getBid(), that.getBid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAuction(), getBid());
    }
}
