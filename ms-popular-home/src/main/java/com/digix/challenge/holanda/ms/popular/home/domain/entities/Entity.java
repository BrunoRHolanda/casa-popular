package com.digix.challenge.holanda.ms.popular.home.domain.entities;

import com.digix.challenge.holanda.ms.popular.home.domain.exceptions.ValidationException;

public interface Entity {
    public void validate() throws ValidationException;
}
