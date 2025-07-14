package com.fabri.srv.api.gateway.config;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableHystrix
@Configuration
@RequiredArgsConstructor
public class APIsRouteConfig {

    private final AuthenticationFilter authenticationFilter;

    @Bean
    RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("srv-oauth", r -> r.path("/srv-oauth/**")
                        .filters(f -> f.filter(authenticationFilter))
                        .uri("lb://srv-oauth"))
                .route("srv-user", r -> r.path("/srv-user/**")
                        .filters(f -> f.filter(authenticationFilter))
                        .uri("lb://srv-user"))
                .route("srv-appointment", r -> r.path("/srv-appointment/**")
                        .filters(f -> f.filter(authenticationFilter))
                        .uri("lb://srv-appointment"))
                .build();
    }
}
