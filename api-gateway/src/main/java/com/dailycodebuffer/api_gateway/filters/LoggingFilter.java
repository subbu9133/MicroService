package com.dailycodebuffer.api_gateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class LoggingFilter implements GlobalFilter, Ordered {

    private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, org.springframework.cloud.gateway.filter.GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();

        if (path.startsWith("/department")) {
            logger.info("📌 Department Service hit the API Gateway");
        } else if (path.startsWith("/employee")) {
            logger.info("📌 Employee Service hit the API Gateway");
        } else {
            logger.info("📌 Request passed through API Gateway: {}", path);
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1; // Ensures the filter runs before other filters
    }
}
