package com.digix.challenge.holanda.ms.popular.home.domain.entities;

import com.digix.challenge.holanda.ms.popular.home.domain.exceptions.InvalidCodeException;
import com.digix.challenge.holanda.ms.popular.home.domain.exceptions.InvalidNameException;
import com.digix.challenge.holanda.ms.popular.home.domain.exceptions.ValidationException;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;
import java.util.UUID;

@Data
public class City extends Locale {

    @NonNull
    private State state;

    public City() {

    }

    public City(@NonNull State state, @NonNull String name, @NonNull Integer code) {
        super(name, code);

        this.state = state;
    }

    public City(@NonNull State state, @NonNull String name, @NonNull Integer code, UUID id, boolean active, Date createdAt, Date updatedAt) {
        super(name, code, id, active, createdAt, updatedAt);

        this.state = state;
    }

    @Override
    public void validate() throws ValidationException {
        super.validate();

        this.state.validate();
    }
}
