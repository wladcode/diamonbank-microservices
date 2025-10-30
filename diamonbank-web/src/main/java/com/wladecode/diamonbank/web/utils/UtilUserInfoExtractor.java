package com.wladecode.diamonbank.web.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;

@Component
public class UtilUserInfoExtractor {
    public boolean isAuthenticated() {
        return
        SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
            && !SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser");
    }
    public String getUsername() {
        OidcUser jwt = (OidcUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return jwt.getClaimAsString("preferred_username");
    }

    public String getEmail() {
        OidcUser jwt = (OidcUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return jwt.getClaimAsString("email");
    }

    public String getUserId() {
        OidcUser jwt = (OidcUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return jwt.getSubject();
    }

    public Map<String, Object> getAllClaims() {
        OidcUser jwt = (OidcUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return jwt.getClaims();
    }

    /**
     * Gets the remaining time until the JWT token expires
     * @return Duration object representing the remaining time until token expiration
     */
    public Duration getTokenExpirationTime() {
        OidcUser jwt = (OidcUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Instant expirationTime = jwt.getExpiresAt();
        if (expirationTime == null) {
            return Duration.ZERO;
        }
        return Duration.between(Instant.now(), expirationTime);
    }

    /**
     * Gets the remaining time in milliseconds until the JWT token expires
     * @return long value representing milliseconds until token expiration, or 0 if already expired
     */
    public long getTokenExpirationTimeMillis() {
        Duration remaining = getTokenExpirationTime();
        return remaining.isNegative() ? 0 : remaining.toMillis();
    }
}
