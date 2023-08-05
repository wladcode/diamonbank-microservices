package com.diamoncode.diamonbankapigateway.filters.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.TokenRelayGatewayFilterFactory;

public class TokenGatewayFilterFactory extends AbstractGatewayFilterFactory<TokenGatewayFilterFactory.ConfigClass> {

    @Autowired
    private TokenRelayGatewayFilterFactory filterFactory;

    @Override
    public GatewayFilter apply(ConfigClass config) {
        return null;
    }

    public static class ConfigClass {

    }
}
