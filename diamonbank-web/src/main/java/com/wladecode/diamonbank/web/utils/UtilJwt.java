package com.wladecode.diamonbank.web.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Slf4j
public class UtilJwt {


    public static String getJwtAccessToken (OidcUser oidcUser){
        log.info("\n=== get access token with oidcUser ===");
        log.info("OidcUser: {}", oidcUser);
        log.info("OidcUser.getAttributes: {}", oidcUser.getAttributes());
        log.info("OidcUser.getClaims: {}", oidcUser.getClaims());
        log.info("OidcUser.getIdToken: {}", oidcUser.getIdToken());

        OidcIdToken idToken = oidcUser.getIdToken();
        log.info("OidcIdToken: {}", idToken);
        if (idToken == null) {
            throw new IllegalStateException("ID token is missing from the principal");
        }

        String tokenValue = idToken.getTokenValue();
        log.info("tokenValue: {}", tokenValue);
        return tokenValue;
    }

    public static String getJwtAccessToken(OAuth2User oAuth2User) {

        DefaultOidcUser oidcUser = getOidcUser(oAuth2User);
        if (oidcUser.getIdToken() == null) {
            throw new IllegalStateException("ID token is missing from the principal");
        }

        // 2. Extract user information
        return oidcUser.getIdToken().getTokenValue();
    }

    private static DefaultOidcUser getOidcUser(OAuth2User oAuth2User) {
        // 1. Validate principal and extract token
        if (!(oAuth2User instanceof DefaultOidcUser)) {
            throw new IllegalStateException("Unexpected principal type: " + oAuth2User.getClass().getName());
        }

        return  (DefaultOidcUser) oAuth2User;
    }

    public static String getSubject(OAuth2User oAuth2User) {
        DefaultOidcUser oidcUser = getOidcUser(oAuth2User);
        return oidcUser.getSubject();
    }

    public  static String getJwtToken(Authentication authentication, OAuth2AuthorizedClientService authorizedClientService){
        OAuth2AuthenticationToken oauth2Token = (OAuth2AuthenticationToken) authentication;
        OAuth2AuthorizedClient oAuth2AuthorizedClient = authorizedClientService.loadAuthorizedClient(oauth2Token.getAuthorizedClientRegistrationId(), oauth2Token.getName());
        String jwtToken =  oAuth2AuthorizedClient.getAccessToken().getTokenValue();

        log.info("\n=== get token with authentication ===");
        log.info("authentication: {}", authentication);
        log.info("JWT Token: {}", jwtToken);

        return jwtToken;
    }



}
