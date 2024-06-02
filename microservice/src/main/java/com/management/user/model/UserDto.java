package com.management.user.model;


import java.util.Objects;

public class UserDto {
    private Long id;

    private String email;


    private Role role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDto userDto)) return false;
        return Objects.equals(getId(), userDto.getId()) && Objects.equals(getEmail(), userDto.getEmail()) && getRole() == userDto.getRole();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail(), getRole());
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }
}

