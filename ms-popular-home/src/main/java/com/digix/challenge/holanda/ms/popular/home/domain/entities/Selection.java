package com.digix.challenge.holanda.ms.popular.home.domain.entities;

import com.digix.challenge.holanda.ms.popular.home.domain.enuns.RuleType;
import com.digix.challenge.holanda.ms.popular.home.domain.exceptions.UnrelatedRuleException;
import com.digix.challenge.holanda.ms.popular.home.domain.exceptions.ValidationException;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.*;

@Data
@RequiredArgsConstructor
public class Selection implements Entity {
    @NonNull
    private Integer ordinance;

    @NonNull
    private City city;

    @NonNull
    private Date start;

    @NonNull
    private Date end;

    @NonNull
    private Map<RuleType, Rule> rules;

    private ArrayList<FamilySelection> familySelections;

    public void insertRule(Rule rule) {
        this.rules.put(rule.getType(), rule);
    }

    public void deleteRule(Rule rule) throws ValidationException {
        if (!this.rules.containsKey(rule.getType())) {
            throw new UnrelatedRuleException();
        }

        this.rules.remove(rule.getType());
    }

    public ArrayList<FamilySelection> generatePriorityList() {

        this.familySelections.sort((o1, o2) -> o2.calculateScore() - o1.calculateScore());

        return this.familySelections;
    }

    @Override
    public void validate() throws ValidationException {

    }
}
