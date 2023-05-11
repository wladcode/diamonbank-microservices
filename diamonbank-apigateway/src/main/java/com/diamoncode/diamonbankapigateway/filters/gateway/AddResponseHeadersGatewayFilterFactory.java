package com.diamoncode.diamonbankapigateway.filters.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Component
public class AddResponseHeadersGatewayFilterFactory extends AbstractGatewayFilterFactory<AddResponseHeadersGatewayFilterFactory.ConfigClass> {

    final Logger logger = LoggerFactory.getLogger(AddResponseHeadersGatewayFilterFactory.class);

    public AddResponseHeadersGatewayFilterFactory() {
        super(ConfigClass.class);
    }

    @Override
    public GatewayFilter apply(ConfigClass config) {
        logger.info("AddResponseHeadersGatewayFilterFactory EXECUTED");
        return (exchange, chain) -> {
            return chain.filter(exchange)
                    .then(Mono.fromRunnable(() -> {
                        ServerHttpResponse response = exchange.getResponse();
                        response.getHeaders().add("Set-on-gw", "from-gw");
                        logger.info("new header add");

                        if (config.isWithHeaders()) {
                            String[] headers = config.getHeaders().split(",");
                            if (Objects.nonNull(headers) && headers.length > 0) {
                                for (int i = 0; i < headers.length; i++) {
                                    response.getHeaders().add("custome-header-".concat(String.valueOf(i)), headers[i]);
                                }
                            } else {
                                response.getHeaders().add("no-custom-headers", "------");
                            }
                        }else {
                            response.getHeaders().add("no-data-config", ":(");
                        }
                    }));
        };
    }

    public static class ConfigClass {
        private String headers;
        private boolean withHeaders;

        public ConfigClass(String headers, boolean withHeaders) {
            this.headers = headers;
            this.withHeaders = withHeaders;
        }

        public String getHeaders() {
            return headers;
        }

        public void setHeaders(String headers) {
            this.headers = headers;
        }

        public boolean isWithHeaders() {
            return withHeaders;
        }

        public void setWithHeaders(boolean withHeaders) {
            this.withHeaders = withHeaders;
        }
    }
}
