package com.fabri.srvappointment.infra.config;

import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Configuration
@RequiredArgsConstructor
public class FeignAuthConfig {

    private final TokenHolder tokenHolder;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> {
            var attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes != null) {
                String token = attributes.getRequest().getHeader("Authorization");
                if (token != null && !token.isBlank()) {
                    tokenHolder.setToken(token);
                    template.header("Authorization", token);
                }
            } else {
                template.header("Authorization", tokenHolder.getToken());
            }
        };
    }
}

