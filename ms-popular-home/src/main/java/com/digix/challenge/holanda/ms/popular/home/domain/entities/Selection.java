package com.digix.challenge.holanda.ms.popular.home.domain.entities;

import com.digix.challenge.holanda.ms.popular.home.domain.enuns.RuleType;
import com.digix.challenge.holanda.ms.popular.home.domain.exceptions.UnrelatedRuleException;
import com.digix.challenge.holanda.ms.popular.home.domain.exceptions.ValidationException;
import lombok.Data;
import lombok.NonNull;

import java.util.*;

@Data
public class Selection extends Entity {
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

    public Selection() {

    }

    public Selection(@NonNull Integer ordinance, @NonNull City city, @NonNull Date start, @NonNull Date end, @NonNull Map<RuleType, Rule> rules) {
        this.ordinance = ordinance;
        this.city = city;
        this.start = start;
        this.end = end;
        this.rules = rules;
    }

    public Selection(@NonNull Integer ordinance, @NonNull City city, @NonNull Date start, @NonNull Date end, @NonNull Map<RuleType, Rule> rules, UUID id, boolean active, Date createdAt, Date updatedAt) {
        super(id, active, createdAt, updatedAt);

        this.ordinance = ordinance;
        this.city = city;
        this.start = start;
        this.end = end;
        this.rules = rules;
    }

    public void insertRule(Rule rule) {
        this.rules.put(rule.getType(), rule);
    }

    public void deleteRule(Rule rule) throws ValidationException {
        if (!this.rules.containsKey(rule.getType())) {
            throw new UnrelatedRuleException();
        }

        this.rules.remove(rule.getType());
    }

    public ArrayList<Family> generatePriorityList() {
        ArrayList<Family> families = new ArrayList<>();

        this.familySelections.sort(Comparator.comparingInt(FamilySelection::calculateScore));

        this.familySelections.forEach(fs -> families.add(fs.getFamily()));

        return families;
    }

    @Override
    public void validate() throws ValidationException {

    }
}
