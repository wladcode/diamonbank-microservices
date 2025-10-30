package com.wladecode.diamonbank.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
public class RoleMapperConfig {

    @Bean
    public GrantedAuthoritiesMapper userAuthoritiesMapper() {
        return (authorities) -> {
            Set<GrantedAuthority> mappedAuthorities = new HashSet<>();

            authorities.forEach(authority -> {
                if (authority instanceof OidcUserAuthority oidcUserAuthority) {
                    // Extract roles from the ID Token's claims
                    Map<String, Object> claims = oidcUserAuthority.getIdToken().getClaims();

                    // Check for the 'realm_access' claim, which contains realm roles
                    if (claims.containsKey("realm_access")) {
                        Map<String, Object> realmAccess = (Map<String, Object>) claims.get("realm_access");
                        Collection<String> roles = (Collection<String>) realmAccess.get("roles");

                        mappedAuthorities.addAll(
                                roles.stream()
                                        // Spring Security expects roles to be prefixed with 'ROLE_'
                                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()))
                                        .collect(Collectors.toList())
                        );
                    }
                } else {
                    // Keep other authorities like SCOPE_...
                    mappedAuthorities.add(authority);
                }
            });

            return mappedAuthorities;
        };
    }
}