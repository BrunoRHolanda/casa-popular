package com.digix.challenge.holanda.ms.popular.home.application.usecases;

import com.digix.challenge.holanda.ms.popular.home.application.data.models.Rule;
import com.digix.challenge.holanda.ms.popular.home.application.data.models.Selection;
import com.digix.challenge.holanda.ms.popular.home.application.data.models.SelectionRule;
import com.digix.challenge.holanda.ms.popular.home.application.data.repositories.RuleRepository;
import com.digix.challenge.holanda.ms.popular.home.application.data.repositories.SelectionRepository;
import com.digix.challenge.holanda.ms.popular.home.application.requests.SelectionRequest;
import com.digix.challenge.holanda.ms.popular.home.application.responses.RuleResponse;
import com.digix.challenge.holanda.ms.popular.home.application.responses.SelectionResponse;

public class CreateSelectionUseCase implements UseCase<SelectionRequest, SelectionResponse> {
    private final SelectionRepository selectionRepository;
    private final RuleRepository ruleRepository;

    public CreateSelectionUseCase(
            SelectionRepository selectionRepository,
            RuleRepository ruleRepository
    ) {
        this.selectionRepository = selectionRepository;
        this.ruleRepository = ruleRepository;
    }

    @Override
    public SelectionResponse handle(SelectionRequest request) {
        Selection selection = new Selection(
                request.getOrdinance(),
                request.getCityId(),
                request.getStart(),
                request.getEnd()
        );

        for (String ruleId : request.getRules()) {
            selection.addRule(ruleId);
        }

        RuleResponse[] rules = new RuleResponse[selection.getSelectionRules().size()];

        var i = 0;

        for (SelectionRule sr : selection.getSelectionRules()) {
            var rule = this.ruleRepository.findById(sr.getRuleId()).get();

            rules[i] = new RuleResponse(
                    rule.getId(),
                    rule.getCode(),
                    rule.getType().toString(),
                    rule.getActive()
            );
            i++;
        }

        this.selectionRepository.save(selection);

        selection = this.selectionRepository.findById(selection.getId()).get();

        return new SelectionResponse(
                selection.getId(),
                selection.getOrdinance(),
                selection.getStart(),
                selection.getEnd(),
                rules
        );
    }
}
