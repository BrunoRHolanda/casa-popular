package com.digix.challenge.holanda.ms.popular.home.infrastructure.api.v1;


import com.digix.challenge.holanda.ms.popular.home.domain.entities.Suitor;
import com.digix.challenge.holanda.ms.popular.home.domain.valueobjects.Cpf;
import com.digix.challenge.holanda.ms.popular.home.domain.valueobjects.Email;
import com.digix.challenge.holanda.ms.popular.home.domain.valueobjects.Phone;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {
    @GetMapping("/api/v1")
    public String hello() {
        Suitor p = new Suitor(
                new Phone("5567991920871"),
                new Email("bruno@gmail.com"),
                "bruno",
                28,
                new Cpf("05247619145")
        );

        return p.toString();
    }
}
