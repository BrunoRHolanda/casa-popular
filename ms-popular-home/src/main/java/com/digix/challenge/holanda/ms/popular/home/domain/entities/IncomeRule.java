package com.digix.challenge.holanda.ms.popular.home.domain.entities;

import com.digix.challenge.holanda.ms.popular.home.domain.enuns.RuleType;

import java.util.Date;
import java.util.UUID;

public class IncomeRule  extends BaseRule {
    public IncomeRule() {
        super(1, RuleType.INCOME);
    }

    public IncomeRule(String id, boolean active, Date createdAt, Date updatedAt) {
        super(1, RuleType.INCOME, id, active, createdAt, updatedAt);
    }

    @Override
    public int defineScore(Family family) {
        if (family.getIncome() <= 900) {
            return 5;
        } else if (family.getIncome() > 900 && family.getIncome() <= 1500) {
            return 3;
        } else {
            return 0;
        }
    }
}
