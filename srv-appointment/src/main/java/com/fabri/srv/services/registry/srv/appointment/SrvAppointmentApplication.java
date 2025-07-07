package com.fabri.srv.services.registry.srv.appointment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SrvAppointmentApplication {
    public static void main(String[] args) {
        SpringApplication.run(SrvAppointmentApplication.class);
    }
}