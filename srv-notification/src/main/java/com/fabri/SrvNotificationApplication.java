package com.fabri;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class SrvNotificationApplication {
    public static void main(String[] args) {
        SpringApplication.run(SrvNotificationApplication.class);
    }
}