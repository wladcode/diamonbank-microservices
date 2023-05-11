package com.diamoncode.diamonbankapigateway.filters.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.web.server.ServerWebExchange;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

public class Utils {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.US);
    public static final String CORRELATION_ID = "diamoncode_correlation_id";

    public static boolean isCorrelationIdPresent(HttpHeaders requestHeaders) {
        if (Objects.nonNull(getCorrelationId(requestHeaders))) {
            return true;

        } else {
            return false;
        }
    }

    public static String getCorrelationId(HttpHeaders requestHeaders) {
        if (Objects.nonNull(requestHeaders.get(CORRELATION_ID))) {
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
