package com.wladecode.diamoncode.apigateway.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

@Order(1)
@Component
@Slf4j
public class RequestTraceFilter  implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpHeaders requestHeaders = exchange.getRequest().getHeaders();

        if (UtilsFilter.isCorrelationIdPresent(requestHeaders)) {
            log.debug("correlation Id found on request -  {} ", UtilsFilter.getCorrelationId(requestHeaders));
        } else {
            String correlationId = UtilsFilter.generateCorrelationId();
            exchange = UtilsFilter.setCorrelationId(exchange, correlationId);
            log.debug("correlation Id generated on request -  {} ", correlationId);
        }

        return chain.filter(exchange);
    }
}
