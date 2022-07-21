package com.digix.challenge.holanda.ms.popular.home.application.usecases;

import com.digix.challenge.holanda.ms.popular.home.application.data.models.Selection;
import com.digix.challenge.holanda.ms.popular.home.application.data.repositories.FamilyRepository;
import com.digix.challenge.holanda.ms.popular.home.application.data.repositories.SelectionRepository;
import com.digix.challenge.holanda.ms.popular.home.application.requests.SubscribeRequest;
import com.digix.challenge.holanda.ms.popular.home.application.responses.SubscribeResponse;
import com.digix.challenge.holanda.ms.popular.home.domain.exceptions.ValidationException;
import lombok.AllArgsConstructor;
import lombok.Data;

public class SubscribeFamilyInSelectionUseCase implements UseCase<SubscribeRequest, SubscribeResponse> {
    private final SelectionRepository selectionRepository;

    public SubscribeFamilyInSelectionUseCase(
        SelectionRepository selectionRepository
    ){
        this.selectionRepository = selectionRepository;
    }

    @Override
    public SubscribeResponse handle(SubscribeRequest request) throws ValidationException {
        Selection selection = this.selectionRepository.findById(request.getSelection()).get();

        selection.addFamily(request.getFamily());

        this.selectionRepository.save(selection);

        return new SubscribeResponse(true);
    }
}
