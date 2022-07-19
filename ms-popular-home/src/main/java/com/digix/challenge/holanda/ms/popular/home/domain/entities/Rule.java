package com.digix.challenge.holanda.ms.popular.home.domain.entities;

import com.digix.challenge.holanda.ms.popular.home.domain.enuns.RuleType;

public interface Rule {
    public int defineScore(Family family);

    public Integer getCode();
    public RuleType getType();
}
