package com.digix.challenge.holanda.ms.popular.home.domain.entities.factories;

import com.digix.challenge.holanda.ms.popular.home.domain.entities.DependentsRule;
import com.digix.challenge.holanda.ms.popular.home.domain.entities.IncomeRule;
import com.digix.challenge.holanda.ms.popular.home.domain.entities.Rule;
import com.digix.challenge.holanda.ms.popular.home.domain.enuns.RuleType;
import com.digix.challenge.holanda.ms.popular.home.domain.exceptions.BusinessLogicException;
import com.digix.challenge.holanda.ms.popular.home.domain.exceptions.NotFoundRuleException;

public class RuleFactory {
    public static Rule create(RuleType type) throws BusinessLogicException {
        if (type == RuleType.DEPENDENTS) {
            return new DependentsRule();
        }

        if (type == RuleType.INCOME) {
            return new IncomeRule();
        }

        throw new NotFoundRuleException();
    }
}
