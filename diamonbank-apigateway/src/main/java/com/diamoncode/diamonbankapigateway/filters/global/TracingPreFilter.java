package com.diamoncode.diamonbankapigateway.filters.global;

import com.diamoncode.diamonbankapigateway.filters.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Order(2)
@Component
public class TracingPreFilter implements GlobalFilter {
    final Logger logger = LoggerFactory.getLogger(TracingPreFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        HttpHeaders headers = exchange.getRequest().getHeaders();

        if (Utils.isCorrelationIdPresent(headers)) {
            logger.debug("Correlation-id header found - {}", Utils.getCorrelationId(headers));
        } else {
            String correlationId = Utils.generateCorrelationId();
            exchange = Utils.setCorrelationId(exchange, correlationId);
            logger.debug("Correlation-id header NOT found, It was created as - {}", correlationId);
        }

        return chain.filter(exchange);
    }
}
