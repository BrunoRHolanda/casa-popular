package com.digix.challenge.holanda.ms.popular.home.domain.entities;

import com.digix.challenge.holanda.ms.popular.home.domain.enuns.RuleType;

public interface Rule extends Entity {
    public int defineScore(Family family);

    public Integer getCode();
    public RuleType getType();
}
