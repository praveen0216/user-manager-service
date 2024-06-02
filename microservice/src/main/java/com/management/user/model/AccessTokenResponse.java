package com.management.user.model;

public class AccessTokenResponse {

    private final String accessToken;

    public AccessTokenResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
