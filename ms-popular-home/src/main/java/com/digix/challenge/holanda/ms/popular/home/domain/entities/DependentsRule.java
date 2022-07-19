package com.digix.challenge.holanda.ms.popular.home.domain.entities;

import com.digix.challenge.holanda.ms.popular.home.domain.enuns.RuleType;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class DependentsRule extends BaseRule {
    public DependentsRule() {
        super(2, RuleType.DEPENDENTS);
    }

    public DependentsRule(UUID id, boolean active, Date createdAt, Date updatedAt) {
        super(2, RuleType.DEPENDENTS, id, active, createdAt, updatedAt);
    }

    @Override
    public int defineScore(Family family) {
        AtomicInteger counter = new AtomicInteger();

        family.getDependents().values().forEach(p -> {
            if  (p.getAge() < 18) {
                counter.getAndIncrement();
            }
        });

        if (counter.get() >= 3) {
            return 3;
        } else if (counter.get() == 1 || counter.get() == 2) {
            return 2;
        } else {
            return 0;
        }
    }
}
