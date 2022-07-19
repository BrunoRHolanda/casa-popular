package com.digix.challenge.holanda.ms.popular.home.domain.entities;

import com.digix.challenge.holanda.ms.popular.home.domain.exceptions.ValidationException;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Data
public class FamilySelection extends Entity {

    @NonNull
    private Family family;

    @NonNull
    private Selection selection;

    public FamilySelection() {

    }

    public FamilySelection(@NonNull Family family, @NonNull Selection selection) {
        this.family = family;
        this.selection = selection;
    }

    public FamilySelection(@NonNull Family family, @NonNull Selection selection, UUID id, boolean active, Date createdAt, Date updatedAt) {
        super(id, active, createdAt, updatedAt);

        this.family = family;
        this.selection = selection;
    }

    public int calculateScore() {
        AtomicInteger score = new AtomicInteger();

        this.selection
                .getRules()
                .values()
                .forEach(rule -> score.addAndGet(rule.defineScore(this.family)));

        return score.get();
    }

    @Override
    public void validate() throws ValidationException {

    }
}
