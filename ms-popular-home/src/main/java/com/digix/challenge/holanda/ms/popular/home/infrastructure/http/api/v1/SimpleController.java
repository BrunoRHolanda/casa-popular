package com.digix.challenge.holanda.ms.popular.home.infrastructure.http.api.v1;


import com.digix.challenge.holanda.ms.popular.home.application.data.models.*;
import com.digix.challenge.holanda.ms.popular.home.application.data.repositories.*;
import com.digix.challenge.holanda.ms.popular.home.domain.enuns.RuleType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@RestController
public class SimpleController {

    private final SuitorRepository suitorRepository;
    private final StateRepository stateRepository;
    private final CityRepository cityRepository;
    private final DistrictRepository districtRepository;
    private final PersonRepository personRepository;
    private final AddressRepository addressRepository;
    private final SpouseRepository spouseRepository;
    private final FamilyRepository familyRepository;
    private final RuleRepository ruleRepository;
    private final SelectionRepository selectionRepository;

    private String cityId;

    public SimpleController(
            SuitorRepository suitorRepository,
            StateRepository stateRepository,
            CityRepository cityRepository,
            DistrictRepository districtRepository,
            PersonRepository personRepository,
            AddressRepository addressRepository,
            SpouseRepository spouseRepository,
            FamilyRepository familyRepository,
            RuleRepository ruleRepository,
            SelectionRepository selectionRepository
    ) {
        this.suitorRepository = suitorRepository;
        this.stateRepository = stateRepository;
        this.cityRepository = cityRepository;
        this.districtRepository = districtRepository;
        this.personRepository = personRepository;
        this.addressRepository = addressRepository;
        this.spouseRepository = spouseRepository;
        this.familyRepository = familyRepository;
        this.ruleRepository = ruleRepository;
        this.selectionRepository = selectionRepository;
    }

    private Family makeFamily() {
        State s = new State("Mato Grosso do Sul", 53);

        this.stateRepository.save(s);

        City c = new City("Campo Grande", 1553, s.getId());

        this.cityRepository.save(c);

        this.cityId = c.getId();

        District d = new District("Aero", 12554, c.getId());

        this.districtRepository.save(d);

        Address a = new Address(d.getId(), "Laura Vicuna", "79083580");

        a.setNumber(988);
        a.setComplement("Casa 07");

        this.addressRepository.save(a);

        Person p = new Person("Bruno Holanda", 28);
        p.setCpf("05247619145");

        this.personRepository.save(p);

        Suitor su = new Suitor("bruno@gmail.com", "67992950871", p.getId());

        this.suitorRepository.save(su);

        Person p2 = new Person("Camila", 30);
        p.setCpf("02525716060");

        this.personRepository.save(p2);

        Spouse sp = new Spouse(p2.getId());

        this.spouseRepository.save(sp);

        Family f = new Family(a.getId(), sp.getId(), su.getId());

        Person dependent1 = new Person("Alex", 22);

        this.personRepository.save(dependent1);

        Person dependent2 = new Person("Guilherme", 28);

        this.personRepository.save(dependent2);

        f.addDependent(dependent1);
        f.addDependent(dependent2);

        this.familyRepository.save(f);

        return f;
    }
    @GetMapping("/api/v1/selection")
    public Optional<Selection> family() {
        Family f = this.makeFamily();

        Rule r1 = new Rule(1, RuleType.DEPENDENTS);
        Rule r2 = new Rule(1, RuleType.INCOME);

        this.ruleRepository.save(r1);
        this.ruleRepository.save(r2);

        Date start = new Date(2022, Calendar.JULY, 21);
        Date end = new Date(2022, Calendar.JULY, 22);

        Selection s = new Selection(122456, this.cityId, start, end);

        s.addFamily(f);

        s.addRule(r1);
        s.addRule(r2);

        this.selectionRepository.save(s);

        return this.selectionRepository.findById(s.getId());
    }
}
