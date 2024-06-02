package com.management.user.model;

public class UserFrequency {

    private String email;
    private Long numberOfAuctions;

    public UserFrequency(String email, Long numberOfAuctions) {
        this.email = email;
        this.numberOfAuctions = numberOfAuctions;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getNumberOfAuctions() {
        return numberOfAuctions;
    }

    public void setNumberOfAuctions(Long numberOfAuctions) {
        this.numberOfAuctions = numberOfAuctions;
    }
}
