package com.management.user.output.repository.entity;


import com.management.user.model.Auction;
import com.management.user.model.Bid;
import com.management.user.model.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;


@Getter
@Setter
@Builder
@NoArgsConstructor
@Entity
@Table(name = "tb_user")
public class UserEntity extends CommonEntity {

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "auctioneer")
    private List<Auction> auctions;

    @OneToMany(mappedBy = "participant")
    private List<Bid> bids;


}
