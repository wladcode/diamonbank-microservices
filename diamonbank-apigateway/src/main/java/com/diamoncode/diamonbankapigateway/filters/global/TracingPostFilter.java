package com.diamoncode.diamonbankapigateway.filters.global;

import com.diamoncode.diamonbankapigateway.filters.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Order(3)
@Component
public class TracingPostFilter {
    final Logger logger = LoggerFactory.getLogger(TracingPostFilter.class);

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
                String correlationId = Utils.getCorrelationId(requestHeaders);

                logger.info("correlation Id found on request -  {} ", correlationId);
                exchange.getResponse().getHeaders().add(Utils.CORRELATION_ID, correlationId);
            }));
        };
    }
}
