package com.digix.challenge.holanda.ms.popular.home.infrastructure.http.api.v1;

import com.digix.challenge.holanda.ms.popular.home.application.data.repositories.*;
import com.digix.challenge.holanda.ms.popular.home.application.requests.FamilyRequest;
import com.digix.challenge.holanda.ms.popular.home.application.responses.FamilyResponse;
import com.digix.challenge.holanda.ms.popular.home.application.usecases.CreateFamilyUseCase;
import com.digix.challenge.holanda.ms.popular.home.domain.exceptions.ValidationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FamilyController {
    private final SuitorRepository suitorRepository;
    private final PersonRepository personRepository;
    private final AddressRepository addressRepository;
    private final SpouseRepository spouseRepository;
    private final FamilyRepository familyRepository;

    public FamilyController(
            SuitorRepository suitorRepository,
            PersonRepository personRepository,
            AddressRepository addressRepository,
            SpouseRepository spouseRepository,
            FamilyRepository familyRepository
    ) {
        this.suitorRepository = suitorRepository;
        this.personRepository = personRepository;
        this.addressRepository = addressRepository;
        this.spouseRepository = spouseRepository;
        this.familyRepository = familyRepository;
    }

    @PostMapping(path = "/api/v1/families")
    public FamilyResponse post(@RequestBody FamilyRequest familyRequest) throws ValidationException {
        return (
                new CreateFamilyUseCase(
                    this.suitorRepository,
                    this.personRepository,
                    this.addressRepository,
                    this.spouseRepository,
                    this.familyRepository
                )
        ).handle(familyRequest);
    }
}
