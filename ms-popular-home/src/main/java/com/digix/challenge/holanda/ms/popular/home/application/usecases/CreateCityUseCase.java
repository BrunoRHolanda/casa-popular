package com.digix.challenge.holanda.ms.popular.home.application.usecases;

import com.digix.challenge.holanda.ms.popular.home.application.data.models.City;
import com.digix.challenge.holanda.ms.popular.home.application.data.models.State;
import com.digix.challenge.holanda.ms.popular.home.application.data.repositories.CityRepository;
import com.digix.challenge.holanda.ms.popular.home.application.data.repositories.StateRepository;
import com.digix.challenge.holanda.ms.popular.home.application.requests.CityRequest;
import com.digix.challenge.holanda.ms.popular.home.application.responses.CityResponse;
import com.digix.challenge.holanda.ms.popular.home.application.responses.StateResponse;

public class CreateCityUseCase implements UseCase<CityRequest, CityResponse> {
    private final StateRepository stateRepository;
    private final CityRepository cityRepository;

    public CreateCityUseCase(CityRepository cityRepository, StateRepository stateRepository) {
        this.stateRepository = stateRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    public CityResponse handle(CityRequest request) {
        City city = new City(request.getName(), request.getCode(), request.getStateId());

        this.cityRepository.save(city);

        city = this.cityRepository.findById(city.getId()).get();
        State state = this.stateRepository.findById(city.getStateId()).get();

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
