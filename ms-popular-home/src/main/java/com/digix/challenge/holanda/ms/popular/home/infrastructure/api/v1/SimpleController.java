package com.digix.challenge.holanda.ms.popular.home.infrastructure.api.v1;


import com.digix.challenge.holanda.ms.popular.home.domain.valueobjects.Phone;
import com.digix.challenge.holanda.ms.popular.home.domain.valueobjects.ValueObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {
    @GetMapping("/api/v1")
    public String hello() {
        ValueObject<String> phone = new Phone("67991920871");

        boolean valid = phone.validate();

        if (valid) {
            return "valid";
        } else {
            return "invalid";
        }
    }
}
