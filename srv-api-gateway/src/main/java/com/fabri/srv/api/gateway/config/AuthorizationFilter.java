package com.fabri.srv.api.gateway.config;

import com.fabri.srv.api.gateway.utils.JWTUtils;
import com.fabri.srv.api.gateway.utils.RoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RefreshScope
@Component
@RequiredArgsConstructor
public class AuthorizationFilter implements GatewayFilter {

    private final JWTUtils jwtUtils;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        final var token = request.getHeaders().getFirst("Authorization");

        if (RouteValidator.isDoctorRoute.test(request)) {
            if (!jwtUtils.hasRole(token, RoleEnum.DOCTOR)) return onError(exchange);
        } else if (RouteValidator.isAdminRoute.test(request)) {
            if (!jwtUtils.hasRole(token, RoleEnum.ADMIN)) return onError(exchange);
        } else {
            // If the route does not require authorization, continue the chain
        }

        return chain.filter(exchange);
    }

    private static Mono<Void> onError(ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.FORBIDDEN);
        return response.setComplete();
    }
}
