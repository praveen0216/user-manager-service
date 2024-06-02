package com.management.user.security;

import com.management.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class CustomUserDetailService  implements UserDetailsService {

    private final UserService userService;

    public CustomUserDetailService( UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userService.findByEmail(username);

        return new UserPrincipal(user.getId(),
                user.getEmail(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority(user.getRole().name())));
    }

   /* @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("auctioneer".equals(username)) {
            return new User("auctioneer", new BCryptPasswordEncoder().encode("password"), Collections.singleton(() -> "ROLE_AUCTIONEER"));
        } else if ("participant".equals(username)) {
            return new User("participant", new BCryptPasswordEncoder().encode("password"), Collections.singleton(() -> "ROLE_PARTICIPANT"));
        } else if ("admin".equals(username)) {
            return new User("admin", new BCryptPasswordEncoder().encode("password"), Collections.singleton(() -> "ROLE_ADMINISTRATOR"));
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }*/
}
