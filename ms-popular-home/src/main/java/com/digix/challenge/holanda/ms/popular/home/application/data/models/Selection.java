package com.digix.challenge.holanda.ms.popular.home.application.data.models;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class Selection extends Model {
    @NonNull
    private Integer ordinance;
    @NonNull
    private String cityId;
    @NonNull
    private Date start;
    @NonNull
    private Date end;

    @MappedCollection(idColumn = "selection_id", keyColumn = "id")
    private Set<FamilySelection> familySelections = new HashSet<>();

    @MappedCollection(idColumn = "selection_id", keyColumn = "id")
    private Set<SelectionRule> selectionRules = new HashSet<>();

    public void addFamily(Family family) {
        this.familySelections.add(new FamilySelection(family.getId()));
    }
    public void addRule(Rule rule) {
        this.selectionRules.add(new SelectionRule(rule.getId()));
    }
}
