package com.management.user.model;

import java.util.List;

public class Report {

    private long auctionNumber;
    private List<Auction> auctions;
    private List<User> participants;
    private List<User> auctioneers;

    // Private constructor to enforce the use of the builder
    private Report() {}

    public long getAuctionNumber() {
        return auctionNumber;
    }

    public void setAuctionNumber(long auctionNumber) {
        this.auctionNumber = auctionNumber;
    }

    public List<Auction> getAuctions() {
        return auctions;
    }

    public void setAuctions(List<Auction> auctions) {
        this.auctions = auctions;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }

    public List<User> getAuctioneers() {
        return auctioneers;
    }

    public void setAuctioneers(List<User> auctioneers) {
        this.auctioneers = auctioneers;
    }

    // Builder method
    public static Builder builder() {
        return new Builder();
    }

    // Builder class
    public static class Builder {
        private long auctionNumber;
        private List<Auction> auctions;
        private List<User> participants;
        private List<User> auctioneers;

        public Builder auctionNumber(long auctionNumber) {
            this.auctionNumber = auctionNumber;
            return this;
        }

        public Builder auctions(List<Auction> auctions) {
            this.auctions = auctions;
            return this;
        }

        public Builder participants(List<User> participants) {
            this.participants = participants;
            return this;
        }

        public Builder auctioneers(List<User> auctioneers) {
            this.auctioneers = auctioneers;
            return this;
        }

        public Report build() {
            Report report = new Report();
            report.setAuctionNumber(this.auctionNumber);
            report.setAuctions(this.auctions);
            report.setParticipants(this.participants);
            report.setAuctioneers(this.auctioneers);
            return report;
        }
    }
}

