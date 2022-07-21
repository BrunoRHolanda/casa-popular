package com.digix.challenge.holanda.ms.popular.home.infrastructure.http.api.v1;

import com.digix.challenge.holanda.ms.popular.home.application.data.repositories.RuleRepository;
import com.digix.challenge.holanda.ms.popular.home.application.data.repositories.SelectionRepository;
import com.digix.challenge.holanda.ms.popular.home.application.requests.SelectionRequest;
import com.digix.challenge.holanda.ms.popular.home.application.responses.SelectionResponse;
import com.digix.challenge.holanda.ms.popular.home.application.usecases.CreateSelectionUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SelectionController {
    private final SelectionRepository selectionRepository;
    private final RuleRepository ruleRepository;

    public SelectionController(
            SelectionRepository selectionRepository,
            RuleRepository ruleRepository
    ) {
        this.selectionRepository = selectionRepository;
        this.ruleRepository = ruleRepository;
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
}
