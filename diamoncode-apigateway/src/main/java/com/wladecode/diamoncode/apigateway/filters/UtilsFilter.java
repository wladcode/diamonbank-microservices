package com.wladecode.diamoncode.apigateway.filters;

import org.springframework.http.HttpHeaders;
import org.springframework.web.server.ServerWebExchange;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UtilsFilter {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.US);
    public static final String CORRELATION_ID = "diamoncode_correlation_id";

    public static boolean isCorrelationIdPresent(HttpHeaders requestHeaders) {
        return getCorrelationId(requestHeaders) != null;
    }

    public static String getCorrelationId(HttpHeaders requestHeaders) {
        if (requestHeaders.get(CORRELATION_ID) != null) {
            List<String> requestHeadersList = requestHeaders.get(CORRELATION_ID);
            return requestHeadersList.stream().findFirst().get();
        } else {
            return null;
        }
    }

    public static String generateCorrelationId() {
        return UUID.randomUUID().toString();
    }

    public static ServerWebExchange setCorrelationId(ServerWebExchange exchange, String correlationId) {
        return exchange.mutate().
                request(
                        exchange.getRequest().mutate().header(CORRELATION_ID, correlationId).build())
                .build();
    }

}
