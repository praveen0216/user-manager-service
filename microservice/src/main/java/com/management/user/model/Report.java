package com.management.user.model;

import java.util.List;
import java.util.Map;

public class Report {

    private Map<Status, List<Auction>> auctionsPerStatus;

    private List<UserFrequency> bidsPerUser;
    private List<UserFrequency> auctionsPerAuctioneer;


    // Private constructor to enforce the use of the builder
    public Report() {}

    public Map<Status, List<Auction>> getAuctionsPerStatus() {
        return auctionsPerStatus;
    }

    public void setAuctionsPerStatus(Map<Status, List<Auction>> auctionsPerStatus) {
        this.auctionsPerStatus = auctionsPerStatus;
    }

    public List<UserFrequency> getBidsPerUser() {
        return bidsPerUser;
    }

    public void setBidsPerUser(List<UserFrequency> bidsPerUser) {
        this.bidsPerUser = bidsPerUser;
    }

    public List<UserFrequency> getAuctionsPerAuctioneer() {
        return auctionsPerAuctioneer;
    }

    public void setAuctionsPerAuctioneer(List<UserFrequency> auctionsPerAuctioneer) {
        this.auctionsPerAuctioneer = auctionsPerAuctioneer;
    }
}

