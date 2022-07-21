package com.digix.challenge.holanda.ms.popular.home.domain.entities;

import com.digix.challenge.holanda.ms.popular.home.domain.exceptions.ValidationException;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.atomic.AtomicInteger;

@Data
@RequiredArgsConstructor
public class FamilySelection implements Entity {

    @NonNull
    private Family family;

    @NonNull
    private Selection selection;

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
