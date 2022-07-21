package com.digix.challenge.holanda.ms.popular.home.application.data.models;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.util.HashSet;
import java.util.Set;

@Data
public class Family extends Model {
    @NonNull
    private String addressId;
    @NonNull
    private String spouseId;
    @NonNull
    private String suitorId;
    @NonNull
    private Float income;

    @MappedCollection(idColumn = "family_id", keyColumn = "id")
    private Set<Dependents> dependents = new HashSet<>();

    public void addDependent(Person person) {
        this.dependents.add(new Dependents(person.getId()));
    }
}
