package com.diamoncode.diamonbankapigateway.filters.global;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

//@Component
public class LoggingGlobalPostFilter {
    final Logger logger = LoggerFactory.getLogger(LoggingGlobalPostFilter.class);

    /**
     * We must define a Bean with a Lamba expresion tu allow a POST FILTER
     *
     * @param exchange
     * @param chain
     * @return
     */
    @Bean
    public GlobalFilter postGlobalFilter() {
        return (exchange, chain) -> {
            return chain.filter(exchange)
                    .then(Mono.fromRunnable(() -> {
                        logger.info("Global Loggin POST Filter EXECUTED");
                        logger.info("Path executed {}", exchange.getRequest().getURI().getPath());
                    }));
        };
    }
}
