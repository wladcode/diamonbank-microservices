package com.wladecode.diamoncode.apigateway.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import reactor.core.publisher.Mono;


@Configuration
@Slf4j
public class ResponseTraceFilter {

    /**
     * We must define a Bean with a Lamba expresion tu allow a POST FILTER
     *
     * @return
     */
    @Bean
    public GlobalFilter postGlobalFilter() {
        return (exchange, chain) -> {
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                HttpHeaders requestHeaders = exchange.getRequest().getHeaders();
                String correlationId = UtilsFilter.getCorrelationId(requestHeaders);

                log.info("correlation Id for response headers -  {} ", correlationId);
                exchange.getResponse().getHeaders().add(UtilsFilter.CORRELATION_ID, correlationId);
            }));
        };
    }
}
