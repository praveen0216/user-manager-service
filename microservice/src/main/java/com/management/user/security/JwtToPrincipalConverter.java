package com.management.user.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtToPrincipalConverter {

    public UserPrincipal convert(DecodedJWT jwt) {
        UserPrincipal userPrincipal = new UserPrincipal(Long.valueOf(jwt.getSubject()),
                jwt.getClaim("email").asString(),
                jwt.getClaim("password").asString(),
                extractAuthoritiesFromClaim(jwt)
        );
        return userPrincipal;
    }

    private List<SimpleGrantedAuthority> extractAuthoritiesFromClaim(DecodedJWT jwt) {
        var claim = jwt.getClaim("role");
        if (claim.isNull() || claim.isMissing()) return List.of();
        return claim.asList(SimpleGrantedAuthority.class);
    }

}
