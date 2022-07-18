package com.digix.challenge.holanda.ms.popular.home.domain.entities;

import com.digix.challenge.holanda.ms.popular.home.domain.exceptions.InvalidIncomeException;
import com.digix.challenge.holanda.ms.popular.home.domain.exceptions.UnrelatedDependentException;
import com.digix.challenge.holanda.ms.popular.home.domain.exceptions.ValidationException;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Data
public class Family  extends Entity {
    @NonNull
    private Suitor suitor;

    @NonNull
    private Person spouse;

    private Map<UUID, Person> dependents;

    @NonNull
    private Address address;

    @NonNull
    private Float income;

    public Family() {

    }

    public Family(@NonNull Suitor suitor, @NonNull Person spouse, @NonNull Address address, @NonNull Float income) {
        this.suitor = suitor;
        this.spouse = spouse;
        this.address = address;
        this.income = income;
    }

    public Family(@NonNull Suitor suitor, @NonNull Person spouse, @NonNull Address address, @NonNull Float income, UUID id, boolean active, Date createdAt, Date updatedAt) {
        super(id, active, createdAt, updatedAt);

        this.suitor = suitor;
        this.spouse = spouse;
        this.address = address;
        this.income = income;
    }

    public void addDependent(Person dependent) throws ValidationException {
        dependent.validate();

        if (this.dependents == null) {
            this.dependents = new HashMap<>();
        }

        this.dependents.put(dependent.getId(), dependent);
    }

    public void removeDependent(Person dependent) throws ValidationException {
        assert this.dependents != null;

        if (!this.dependents.containsKey(dependent.getId())) {
            throw new UnrelatedDependentException();
        }
    }

    public boolean isDependent(Person dependent) {
        if (this.dependents == null) {
            return false;
        }

        return this.dependents.containsKey(dependent.getId());
    }

    @Override
    public void validate() throws ValidationException {
        this.address.validate();
        this.suitor.validate();
        this.spouse.validate();

        if (this.dependents != null) {
            for (Person p : this.dependents.values()) {
                p.validate();
            }
        }

        if (this.income < 0) {
            throw new InvalidIncomeException();
        }
    }
}
