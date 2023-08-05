package com.diamoncode.diamonbank.accounts.adapter.in.web.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class KeycloackRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {

        Map<String, Object> realmAccess = (Map<String, Object>) jwt.getClaims().get("realm_access");
        if (Objects.isNull(realmAccess) || realmAccess.isEmpty()) {
            return new ArrayList<>();
        }

        return ((List<String>) realmAccess.get("roles"))
                .stream().map(rolName -> "ROLE_" + rolName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

    }

}
