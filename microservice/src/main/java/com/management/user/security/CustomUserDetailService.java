package com.management.user.security;

import com.management.user.service.UserAuthService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomUserDetailService  implements UserDetailsService {

    private final UserAuthService userAuthService;

    public CustomUserDetailService(UserAuthService userAuthService) {
        this.userAuthService = userAuthService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userAuthService.findByEmail(username).orElseThrow();

        return new UserPrincipal(user.getId(),
                user.getEmail(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority(user.getRole())));
    }
}
