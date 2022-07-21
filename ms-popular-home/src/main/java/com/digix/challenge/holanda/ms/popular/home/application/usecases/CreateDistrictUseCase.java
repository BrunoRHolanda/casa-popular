package com.digix.challenge.holanda.ms.popular.home.application.usecases;

import com.digix.challenge.holanda.ms.popular.home.application.data.models.City;
import com.digix.challenge.holanda.ms.popular.home.application.data.models.District;
import com.digix.challenge.holanda.ms.popular.home.application.data.models.State;
import com.digix.challenge.holanda.ms.popular.home.application.data.repositories.CityRepository;
import com.digix.challenge.holanda.ms.popular.home.application.data.repositories.DistrictRepository;
import com.digix.challenge.holanda.ms.popular.home.application.data.repositories.StateRepository;
import com.digix.challenge.holanda.ms.popular.home.application.requests.DistrictRequest;
import com.digix.challenge.holanda.ms.popular.home.application.responses.CityResponse;
import com.digix.challenge.holanda.ms.popular.home.application.responses.DistrictResponse;
import com.digix.challenge.holanda.ms.popular.home.application.responses.StateResponse;
import com.digix.challenge.holanda.ms.popular.home.domain.exceptions.ValidationException;

public class CreateDistrictUseCase implements  UseCase<DistrictRequest, DistrictResponse> {
    private final StateRepository stateRepository;
    private final CityRepository cityRepository;
    private final DistrictRepository districtRepository;

    public CreateDistrictUseCase(
            CityRepository cityRepository,
            StateRepository stateRepository,
            DistrictRepository districtRepository
    ) {
        this.stateRepository = stateRepository;
        this.cityRepository = cityRepository;
        this.districtRepository = districtRepository;
    }

    @Override
    public DistrictResponse handle(DistrictRequest request) throws ValidationException {
        District district = new District(request.getName(), request.getCode(), request.getCityId());

        City city = this.cityRepository.findById(district.getCityId()).get();
        State state = this.stateRepository.findById(city.getStateId()).get();

        com.digix.challenge.holanda.ms.popular.home.domain.entities.District domainDistrict =
                new com.digix.challenge.holanda.ms.popular.home.domain.entities.District(
                        new com.digix.challenge.holanda.ms.popular.home.domain.entities.City(
                                new com.digix.challenge.holanda.ms.popular.home.domain.entities.State(
                                        state.getName(),
                                        state.getCode()
                                ),
                                city.getName(),
                                city.getCode()
                        ),
                        request.getName(),
                        request.getCode()
                );

        domainDistrict.validate();

        this.districtRepository.save(district);

        district = this.districtRepository.findById(district.getId()).get();

        return new DistrictResponse(
                district.getId(),
                district.getName(),
                district.getCode(),
                new CityResponse(
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
                ),
                district.getActive()
        );
    }
}
