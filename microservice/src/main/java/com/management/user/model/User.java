package com.management.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class User {
    private Long id;

    @NotEmpty(message = "Username is required")
    private String username;

    @NotEmpty(message = "Password is required")
    @JsonIgnore
    private String password;

    @NotEmpty(message = "Role is required [AUCTIONEER, PARTICIPANT, ADMIN]")
    private Role role;


}

