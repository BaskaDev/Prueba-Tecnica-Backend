package com.prueba.backend.controller;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Mono;

@Component
public class CustomRateLimitFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        return chain.filter(exchange)
                .onErrorResume(ResponseStatusException.class, e -> {
                    if (e.getStatusCode() == HttpStatus.TOO_MANY_REQUESTS) {

                        exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
                        return exchange.getResponse().setComplete();
                    }
                    return Mono.error(e);
                });
    }
}
