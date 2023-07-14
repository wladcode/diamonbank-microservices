package com.diamoncode.diamonbankapigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange(exchanges -> exchanges.pathMatchers("/dc-api/accounts/**").authenticated()
                .pathMatchers("/dc-api/cards/**").authenticated()
                .pathMatchers("/dc-api/loans/**").permitAll()
        ).oauth2ResourceServer().jwt();

        http.csrf().disable();
        return http.build();
    }
}
