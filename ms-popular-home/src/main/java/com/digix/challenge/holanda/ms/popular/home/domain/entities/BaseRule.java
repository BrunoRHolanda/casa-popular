package com.digix.challenge.holanda.ms.popular.home.domain.entities;

import com.digix.challenge.holanda.ms.popular.home.domain.enuns.RuleType;
import com.digix.challenge.holanda.ms.popular.home.domain.exceptions.InvalidCodeException;
import com.digix.challenge.holanda.ms.popular.home.domain.exceptions.ValidationException;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@RequiredArgsConstructor
public abstract class BaseRule implements Rule {

    @NonNull
    private Integer code;

    @NonNull
    private RuleType type;

    public void validate() throws ValidationException  {
        if (this.code < 0) {
            throw new InvalidCodeException();
        }
    }
}
