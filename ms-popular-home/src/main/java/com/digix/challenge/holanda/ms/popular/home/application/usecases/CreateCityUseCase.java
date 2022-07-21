package com.digix.challenge.holanda.ms.popular.home.application.usecases;

import com.digix.challenge.holanda.ms.popular.home.application.data.models.City;
import com.digix.challenge.holanda.ms.popular.home.application.data.models.State;
import com.digix.challenge.holanda.ms.popular.home.application.data.repositories.CityRepository;
import com.digix.challenge.holanda.ms.popular.home.application.data.repositories.StateRepository;
import com.digix.challenge.holanda.ms.popular.home.application.requests.CityRequest;
import com.digix.challenge.holanda.ms.popular.home.application.responses.CityResponse;
import com.digix.challenge.holanda.ms.popular.home.application.responses.StateResponse;
import com.digix.challenge.holanda.ms.popular.home.domain.exceptions.ValidationException;

public class CreateCityUseCase implements UseCase<CityRequest, CityResponse> {
    private final StateRepository stateRepository;
    private final CityRepository cityRepository;

    public CreateCityUseCase(CityRepository cityRepository, StateRepository stateRepository) {
        this.stateRepository = stateRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    public CityResponse handle(CityRequest request) throws ValidationException {
        City city = new City(request.getName(), request.getCode(), request.getStateId());

        State state = this.stateRepository.findById(city.getStateId()).get();

        com.digix.challenge.holanda.ms.popular.home.domain.entities.City domainCity =
                new com.digix.challenge.holanda.ms.popular.home.domain.entities.City(
                        new com.digix.challenge.holanda.ms.popular.home.domain.entities.State(
                                state.getName(),
                                state.getCode()
                        ),
                        request.getName(),
                        request.getCode()
                );

        domainCity.validate();

        this.cityRepository.save(city);

        city = this.cityRepository.findById(city.getId()).get();

        return new CityResponse(
                city.getId(),
                city.getName(),
                city.getCode(),
                new StateResponse(
                        state.getId(),
                        state.getName(),
                        state.getCode(),
                        state.getActive()
                ),
                city.getActive()
        );
    }
}
