package com.wladecode.diamonbank.web.config;

import com.wladecode.diamonbank.web.utils.UtilUserInfoExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final ClientRegistrationRepository clientRegistrationRepository;
    private final LoginSuccessHandler loginSuccessHandler;
    private final UtilUserInfoExtractor utilUserInfoExtractor;

    public SecurityConfig(ClientRegistrationRepository clientRegistrationRepository, LoginSuccessHandler loginSuccessHandler,
                          UtilUserInfoExtractor utilUserInfoExtractor
    ) {
        this.clientRegistrationRepository = clientRegistrationRepository;
        this.loginSuccessHandler = loginSuccessHandler;
        this.utilUserInfoExtractor = utilUserInfoExtractor;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/webjars/**", "/css/**", "/js/**").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2Login(Customizer.withDefaults())
                .logout(logout -> logout
                        //.logoutSuccessUrl("/?logout=true")
                        .logoutSuccessHandler(oidcLogoutSuccessHandler())
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID")

                );
            /*.exceptionHandling(exception -> exception
                .authenticationEntryPoint((request, response, authException) -> {
                    // For API calls, return 401
                    if (request.getRequestURI().startsWith("/api/")) {
                        response.sendError(HttpStatus.UNAUTHORIZED.value(), "Unauthorized");
                    } else {
                        // For web pages, redirect to index
                        response.sendRedirect("/?expired=true");
                    }
                })
            );
             */

        return http.build();
    }

    private OidcClientInitiatedLogoutSuccessHandler oidcLogoutSuccessHandler() {
        OidcClientInitiatedLogoutSuccessHandler successHandler =
                new OidcClientInitiatedLogoutSuccessHandler(clientRegistrationRepository);

        // Configure the URI that Keycloak will redirect to after logout
        successHandler.setPostLogoutRedirectUri("{baseUrl}/");

        return successHandler;
    }
}
