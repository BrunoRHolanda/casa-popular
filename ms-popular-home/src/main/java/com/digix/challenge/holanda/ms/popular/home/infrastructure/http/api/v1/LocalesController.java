package com.digix.challenge.holanda.ms.popular.home.infrastructure.http.api.v1;

import com.digix.challenge.holanda.ms.popular.home.application.data.repositories.CityRepository;
import com.digix.challenge.holanda.ms.popular.home.application.data.repositories.DistrictRepository;
import com.digix.challenge.holanda.ms.popular.home.application.data.repositories.StateRepository;
import com.digix.challenge.holanda.ms.popular.home.application.requests.CityRequest;
import com.digix.challenge.holanda.ms.popular.home.application.requests.DistrictRequest;
import com.digix.challenge.holanda.ms.popular.home.application.requests.StateRequest;
import com.digix.challenge.holanda.ms.popular.home.application.responses.CityResponse;
import com.digix.challenge.holanda.ms.popular.home.application.responses.DistrictResponse;
import com.digix.challenge.holanda.ms.popular.home.application.responses.StateResponse;
import com.digix.challenge.holanda.ms.popular.home.application.usecases.CreateCityUseCase;
import com.digix.challenge.holanda.ms.popular.home.application.usecases.CreateDistrictUseCase;
import com.digix.challenge.holanda.ms.popular.home.application.usecases.CreateStateUseCase;
import com.digix.challenge.holanda.ms.popular.home.domain.exceptions.ValidationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocalesController {
    private final StateRepository stateRepository;
    private final CityRepository cityRepository;
    private final DistrictRepository districtRepository;

    public LocalesController(
            StateRepository stateRepository,
            CityRepository cityRepository,
            DistrictRepository districtRepository
    ) {
        this.stateRepository = stateRepository;
        this.cityRepository = cityRepository;
        this.districtRepository = districtRepository;
    }

    @PostMapping(path = "/api/v1/locales/state")
    public StateResponse post(@RequestBody StateRequest stateRequest) throws ValidationException {
        return (new CreateStateUseCase(this.stateRepository)).handle(stateRequest);
    }

    @PostMapping(path = "/api/v1/locales/city")
    public CityResponse post(@RequestBody CityRequest cityRequest) throws ValidationException {
        return (
                new CreateCityUseCase(
                        this.cityRepository,
                        this.stateRepository
                )
        ).handle(cityRequest);
    }

    @PostMapping(path = "/api/v1/locales/district")
    public DistrictResponse post(@RequestBody DistrictRequest districtRequest) throws ValidationException {
        return (
                new CreateDistrictUseCase(
                        this.cityRepository,
                        this.stateRepository,
                        this.districtRepository
                )
        ).handle(districtRequest);
    }
}
