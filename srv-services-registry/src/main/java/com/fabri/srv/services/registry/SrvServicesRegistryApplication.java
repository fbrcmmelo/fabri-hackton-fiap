package com.fabri.srv.services.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class SrvServicesRegistryApplication {
    public static void main(String[] args) {
        SpringApplication.run(SrvServicesRegistryApplication.class);
    }
}