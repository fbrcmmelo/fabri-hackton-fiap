package com.fabri.srv.api.gateway.config;

import org.springframework.http.server.reactive.ServerHttpRequest;

import java.util.Set;
import java.util.function.Predicate;

public class RouteValidator {

    protected static final Set<String> OPEN_ENDPOINTS =  Set.of(
            "/auth",
            "/users/login",
            "/users/register"
            );

    RouteValidator() {
        throw new IllegalStateException("Utility class");
    }

    public static final Predicate<ServerHttpRequest> isSecured = request ->
            OPEN_ENDPOINTS.stream().noneMatch(request.getURI().getPath()::contains);

}
