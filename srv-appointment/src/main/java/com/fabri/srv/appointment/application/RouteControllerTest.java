package com.fabri.srv.appointment.application;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RouteControllerTest {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }
}
