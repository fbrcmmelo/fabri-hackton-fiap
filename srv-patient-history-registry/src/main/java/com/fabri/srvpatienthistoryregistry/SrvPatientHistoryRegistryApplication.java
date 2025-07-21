package com.fabri.srvpatienthistoryregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SrvPatientHistoryRegistryApplication {
    public static void main(String[] args) {
        SpringApplication.run(SrvPatientHistoryRegistryApplication.class);
    }
}