package com.fabri.srv.api.gateway.config;

import org.springframework.http.server.reactive.ServerHttpRequest;

import java.util.Arrays;
import java.util.function.Predicate;

public class RouteValidator {

    protected static final String[] OPEN_ENDPOINTS = {
            "/srv-oauth/",
            "/srv-user/login"
    };

    RouteValidator() {
        throw new IllegalStateException("Utility class");
    }

    public static final Predicate<ServerHttpRequest> isSecured = request -> Arrays.stream(OPEN_ENDPOINTS)
            .noneMatch(endpoint -> request.getURI().getPath().contains(endpoint));

}
