package com.diamoncode.diamonbank.accounts.adapter.in.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // This converter is necessary because the use of roles
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloackRoleConverter());

        http.authorizeHttpRequests(authorize ->
                        authorize
                                .antMatchers("/properties").hasRole("ACCOUNTS")
                                .anyRequest().authenticated())
                .oauth2ResourceServer().jwt()
                //We need a Converter because we are using the role ACCOUNTS
                .jwtAuthenticationConverter(jwtAuthenticationConverter)
        ;

        http.csrf().disable();
        return http.build();

    }
}
