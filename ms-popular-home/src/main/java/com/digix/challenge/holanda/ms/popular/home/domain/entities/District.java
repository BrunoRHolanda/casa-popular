package com.digix.challenge.holanda.ms.popular.home.domain.entities;

import com.digix.challenge.holanda.ms.popular.home.domain.exceptions.ValidationException;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;
import java.util.UUID;

@Data
public class District extends Locale {

    @NonNull
    private City city;

    public District() {

    }

    public District(@NonNull City city, @NonNull String name, @NonNull Integer code) {
        super(name, code);

        this.city = city;
    }

    public District(@NonNull City city, @NonNull String name, @NonNull Integer code, UUID id, boolean active, Date createdAt, Date updatedAt) {
        super(name, code, id, active, createdAt, updatedAt);

        this.city = city;
    }

    @Override
    public void validate() throws ValidationException {
        super.validate();

        this.city.validate();
    }
}
