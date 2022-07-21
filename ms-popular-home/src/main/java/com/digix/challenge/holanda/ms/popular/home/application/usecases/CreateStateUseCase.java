package com.digix.challenge.holanda.ms.popular.home.application.usecases;

import com.digix.challenge.holanda.ms.popular.home.application.data.models.State;
import com.digix.challenge.holanda.ms.popular.home.application.data.repositories.StateRepository;
import com.digix.challenge.holanda.ms.popular.home.application.requests.StateRequest;
import com.digix.challenge.holanda.ms.popular.home.application.responses.StateResponse;

public class CreateStateUseCase implements UseCase<StateRequest, StateResponse> {

    private final StateRepository stateRepository;

    public CreateStateUseCase(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    @Override
    public StateResponse handle(StateRequest request) {
        State state = new State(request.getName(), request.getCode());

        this.stateRepository.save(state);

        state = this.stateRepository.findById(state.getId()).get();

        return new StateResponse(state.getId(), state.getName(), state.getCode(), state.getActive());
    }
}
