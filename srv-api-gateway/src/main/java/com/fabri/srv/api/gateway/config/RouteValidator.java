package com.fabri.srv.api.gateway.config;

import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;

import java.util.Set;
import java.util.function.Predicate;

public class RouteValidator {

    RouteValidator() {
        throw new IllegalStateException("Utility class");
    }

    protected static final Set<String> OPEN_ENDPOINTS = Set.of(
            "/auth",
            "/users/login",
            "/users/register",
            "/users/doctors/register"
    );

    protected static final Set<String> DOCTOR_ENDPOINTS = Set.of(
            "/triages",
            "/appointments",
            "/patients",
            "/next-appointment"
    );

    protected static final Set<String> ADMIN_ENDPOINTS = Set.of(
            "/users/doctors/activate"
    );

    public static final Predicate<ServerHttpRequest> isAdminRoute = request ->
            ADMIN_ENDPOINTS.stream().anyMatch(request.getURI().getPath()::contains);

    public static final Predicate<ServerHttpRequest> isSecured = request ->
            OPEN_ENDPOINTS.stream().noneMatch(request.getURI().getPath()::contains);

    public static final Predicate<ServerHttpRequest> isDoctorRoute = request ->
    {
        String path = request.getURI().getPath();
        if (path.contains("/triages")) {
            return request.getMethod().equals(HttpMethod.PUT);
        }
        return DOCTOR_ENDPOINTS.stream().anyMatch(path::contains);
    };

}
