package com.digix.challenge.holanda.ms.popular.home.infrastructure.api.v1;


import com.digix.challenge.holanda.ms.popular.home.application.repositories.FamilyJdbcRepository;
import com.digix.challenge.holanda.ms.popular.home.application.repositories.FamilyRepository;
import com.digix.challenge.holanda.ms.popular.home.domain.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

    private final FamilyRepository repository;

    @Autowired
    public SimpleController(FamilyJdbcRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/api/v1")
    public String hello() {
        Person p = this.repository.createPerson();

        return p.toString();
    }
}
