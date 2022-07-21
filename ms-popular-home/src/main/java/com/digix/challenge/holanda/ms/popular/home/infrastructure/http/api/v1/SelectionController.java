package com.digix.challenge.holanda.ms.popular.home.infrastructure.http.api.v1;

import com.digix.challenge.holanda.ms.popular.home.application.data.repositories.*;
import com.digix.challenge.holanda.ms.popular.home.application.requests.GenerateBenefitedCatalogRequest;
import com.digix.challenge.holanda.ms.popular.home.application.requests.SelectionRequest;
import com.digix.challenge.holanda.ms.popular.home.application.requests.SubscribeRequest;
import com.digix.challenge.holanda.ms.popular.home.application.responses.BenefitedCatalogResponse;
import com.digix.challenge.holanda.ms.popular.home.application.responses.SelectionResponse;
import com.digix.challenge.holanda.ms.popular.home.application.responses.SubscribeResponse;
import com.digix.challenge.holanda.ms.popular.home.application.usecases.CreateSelectionUseCase;
import com.digix.challenge.holanda.ms.popular.home.application.usecases.GenerateBenefitedCatalogUseCase;
import com.digix.challenge.holanda.ms.popular.home.application.usecases.SubscribeFamilyInSelectionUseCase;
import com.digix.challenge.holanda.ms.popular.home.domain.exceptions.ValidationException;
import com.digix.challenge.holanda.ms.popular.home.infrastructure.http.client.MsBenefitedCatalogHttp;
import org.springframework.web.bind.annotation.*;

@RestController
public class SelectionController {
    private final SelectionRepository selectionRepository;
    private final RuleRepository ruleRepository;
    private final SuitorRepository suitorRepository;
    private final PersonRepository personRepository;
    private final AddressRepository addressRepository;
    private final SpouseRepository spouseRepository;
    private final FamilyRepository familyRepository;
    private final StateRepository stateRepository;
    private final CityRepository cityRepository;
    private final DistrictRepository districtRepository;

    public SelectionController(
            SelectionRepository selectionRepository,
            RuleRepository ruleRepository,
            SuitorRepository suitorRepository,
            PersonRepository personRepository,
            AddressRepository addressRepository,
            SpouseRepository spouseRepository,
            FamilyRepository familyRepository,
            StateRepository stateRepository,
            CityRepository cityRepository,
            DistrictRepository districtRepository
    ) {
        this.selectionRepository = selectionRepository;
        this.ruleRepository = ruleRepository;
        this.suitorRepository = suitorRepository;
        this.personRepository = personRepository;
        this.addressRepository = addressRepository;
        this.spouseRepository = spouseRepository;
        this.familyRepository = familyRepository;
        this.stateRepository = stateRepository;
        this.cityRepository = cityRepository;
        this.districtRepository = districtRepository;
    }

    @PostMapping(path = "/api/v1/selections")
    public SelectionResponse post(@RequestBody SelectionRequest selectionRequest) {
        return (
                new CreateSelectionUseCase(
                        this.selectionRepository,
                        this.ruleRepository
                )
        ).handle(selectionRequest);
    }

    @PatchMapping(path = "/api/v1/selections/{id}/subscription")
    public SubscribeResponse patch(
            @PathVariable String id,
            @RequestBody SubscribeRequest subscribeRequest
    ) throws ValidationException {
        subscribeRequest.setSelection(id);

        return (
                new SubscribeFamilyInSelectionUseCase(
                        this.selectionRepository
                )
        ).handle(subscribeRequest);
    }

    @GetMapping(path = "/api/v1/selections/{id}/publish")
    public void get(@PathVariable String id) throws ValidationException {
        BenefitedCatalogResponse benefitedCatalogResponse = (
                new GenerateBenefitedCatalogUseCase(
                    this.selectionRepository,
                    this.ruleRepository,
                    this.suitorRepository,
                    this.personRepository,
                    this.addressRepository,
                    this.spouseRepository ,
                    this.familyRepository ,
                    this.stateRepository ,
                    this.cityRepository,
                    this.districtRepository
                )
        ).handle(new GenerateBenefitedCatalogRequest(id));

        (new MsBenefitedCatalogHttp()).publishCatalog(benefitedCatalogResponse);
    }
}
