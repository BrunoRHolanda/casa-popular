package com.digix.challenge.holanda.ms.popular.home.infrastructure.api.v1;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {
    @GetMapping("/api/v1")
    public String hello() {
        return "Hello";
    }
}
