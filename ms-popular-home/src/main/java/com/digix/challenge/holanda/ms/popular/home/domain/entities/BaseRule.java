package com.digix.challenge.holanda.ms.popular.home.domain.entities;

import com.digix.challenge.holanda.ms.popular.home.domain.enuns.RuleType;
import com.digix.challenge.holanda.ms.popular.home.domain.exceptions.InvalidCodeException;
import com.digix.challenge.holanda.ms.popular.home.domain.exceptions.ValidationException;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;
import java.util.UUID;

@Data
public abstract class BaseRule extends Entity implements Rule {

    @NonNull
    private Integer code;

    @NonNull
    private RuleType type;

    public BaseRule() {

    }

    public BaseRule(@NonNull Integer code,  @NonNull RuleType type) {
        this.code = code;
        this.type = type;
    }

    public BaseRule(@NonNull Integer code, @NonNull RuleType type, String id, boolean active, Date createdAt, Date updatedAt) {
        super(id, active, createdAt, updatedAt);

        this.code = code;
        this.type = type;
    }

    public void validate() throws ValidationException  {
        if (this.code < 0) {
            throw new InvalidCodeException();
        }
    }
}
