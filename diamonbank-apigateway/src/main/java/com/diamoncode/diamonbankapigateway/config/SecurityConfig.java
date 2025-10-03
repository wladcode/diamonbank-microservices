package com.diamoncode.diamonbankapigateway.config;

import com.diamoncode.diamonbankapigateway.config.converter.RoleConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new RoleConverter());

        http.authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/actuator/**").permitAll()
                        .pathMatchers("/dc-api/accounts/**").authenticated()
                        .pathMatchers("/dc-api/customer/**").authenticated()
                        .pathMatchers("/dc-api/cards/**").authenticated()
                        .pathMatchers("/dc-api/loans/**").permitAll())
                .oauth2ResourceServer(
                        oauth2 -> oauth2.jwt());

        http.csrf().disable();
        return http.build();
    }
}
