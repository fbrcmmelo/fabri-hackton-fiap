package com.fabri.srv.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SrvUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(SrvUserApplication.class);
    }
}