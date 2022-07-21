package com.digix.challenge.holanda.ms.popular.home.domain.entities;

import com.digix.challenge.holanda.ms.popular.home.domain.enuns.RuleType;

public class IncomeRule  extends BaseRule {
    public IncomeRule() {
        super(1, RuleType.INCOME);
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
