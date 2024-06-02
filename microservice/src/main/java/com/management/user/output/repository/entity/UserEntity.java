package com.management.user.output.repository.entity;


import com.management.user.model.Role;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;
import java.util.Objects;



@Entity
@Table(name = "tb_users")
@EntityListeners(AuditingEntityListener.class)
public class UserEntity extends CommonEntity {

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "auctioneer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AuctionEntity> auctions;

    @OneToMany(mappedBy = "participant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BidEntity> bids;


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<AuctionEntity> getAuctions() {
        return auctions;
    }

    public void setAuctions(List<AuctionEntity> auctions) {
        this.auctions = auctions;
    }

    public List<BidEntity> getBids() {
        return bids;
    }

    public void setBids(List<BidEntity> bids) {
        this.bids = bids;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEntity that)) return false;
        return getRole() == that.getRole() && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getPassword(), that.getPassword()) && Objects.equals(getAuctions(), that.getAuctions()) && Objects.equals(getBids(), that.getBids());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRole(), getEmail(), getPassword(), getAuctions(), getBids());
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "role=" + role +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", auctions=" + auctions +
                ", bids=" + bids +
                '}';
    }
}
