package com.diamoncode.diamonbankapigateway.filters.global;

import com.diamoncode.diamonbankapigateway.filters.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.InetSocketAddress;
import java.time.ZonedDateTime;
import java.util.Objects;

@Component
public class LoggingGlobalPreAndPostFilter implements GlobalFilter, Ordered {
    final Logger logger = LoggerFactory.getLogger(LoggingGlobalPreAndPostFilter.class);


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("Global Loggin Pre Filter EXECUTED COMBINED");
        logger.info("Path executed {}", exchange.getRequest().getURI().getPath());

        String zonedDateTime = ZonedDateTime.now().format(Utils.DATE_TIME_FORMATTER);
        long startTime = System.currentTimeMillis();
        HttpMethod httpMethod = exchange.getRequest().getMethod();
        String method = Objects.nonNull(httpMethod) ? httpMethod.name() : "no-method";
        String uri = exchange.getRequest().mutate().build().getPath().value();
        InetSocketAddress inetSocketAddress = exchange.getRequest().getLocalAddress();
        String port = Objects.nonNull(inetSocketAddress) ? String.valueOf(inetSocketAddress.getPort()) : "no-port";


        return chain.filter(exchange)
                .then(Mono.fromRunnable(() -> {
                    logger.info("Global Loggin POST Filter EXECUTED COMBINED");
                    logger.info("Path executed {}", exchange.getRequest().getURI().getPath());

                    Integer status = exchange.getResponse().getRawStatusCode();
                    long executedTime = System.currentTimeMillis() - startTime;

                    InetSocketAddress sa = exchange.getRequest().getRemoteAddress();
                    String address = Objects.isNull(sa) ? sa.getHostString() : "no-remote address";

                    logger.info("Remote address:{}, method:{}, status:{}, port:{}, execTime:{}, url:{}",
                            address, method, status, port, executedTime, uri);

                }));
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
