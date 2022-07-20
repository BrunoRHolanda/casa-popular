package com.digix.challenge.holanda.ms.popular.home.infrastructure.api.v1;


import com.digix.challenge.holanda.ms.popular.home.application.data.models.Person;
import com.digix.challenge.holanda.ms.popular.home.application.data.models.Spouse;
import com.digix.challenge.holanda.ms.popular.home.application.data.models.State;
import com.digix.challenge.holanda.ms.popular.home.application.data.models.Suitor;
import com.digix.challenge.holanda.ms.popular.home.application.data.repositories.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@RestController
public class SimpleController {

    private final SuitorRepository suitorRepository;
    private final StateRepository stateRepository;

    public SimpleController(
            SuitorRepository suitorRepository,
            StateRepository stateRepository
    ) {
        this.suitorRepository = suitorRepository;
        this.stateRepository = stateRepository;
    }

    @GetMapping("/api/v1/suitor")
    public Optional<Suitor> suitor() {
        Person p = new Person("Bruno Holanda", 28);
        p.setCpf("05247619145");
        p.setActive(true);
        p.setCreatedAt(new Date());
        p.setUpdatedAt(new Date());

        Suitor s = new Suitor("bruno@email.com", "5567992950871");

        s.setPerson(p);

        this.suitorRepository.save(s);

        return this.suitorRepository.findById(s.getId());
    }

    @GetMapping("/api/v1/state")
    public Optional<State> state() {
        State s = new State("Mato Grosso do Sul", 53);

        this.stateRepository.save(s);

        return this.stateRepository.findById(s.getId());
    }
}
