package com.diamoncode.diamonbankapigateway.filters.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

@Component
public class BlockedRequestGatewayFilterFactory extends AbstractGatewayFilterFactory<BlockedRequestGatewayFilterFactory.ConfigClass> {

    final Logger logger = LoggerFactory.getLogger(BlockedRequestGatewayFilterFactory.class);

    public BlockedRequestGatewayFilterFactory() {
        super(ConfigClass.class);
    }

    @Override
    public GatewayFilter apply(ConfigClass config) {
        logger.info("BlockedRequestGatewayFilterFactory EXECUTED");
        return (exchange, chain) -> {
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.NOT_FOUND);
            return response.setComplete();
        };
    }

    public static class ConfigClass {
    }
}
