package com.fabri.srvappointment.infra.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class FeignAuthConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> {
            var attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes != null) {
                String token = attributes.getRequest().getHeader("Authorization");
                if (token != null && !token.isBlank()) {
                    template.header("Authorization", token);
                }
            }
        };
    }
}

