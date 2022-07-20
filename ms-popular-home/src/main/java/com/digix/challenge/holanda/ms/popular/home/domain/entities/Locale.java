package com.digix.challenge.holanda.ms.popular.home.domain.entities;

import com.digix.challenge.holanda.ms.popular.home.domain.exceptions.InvalidCodeException;
import com.digix.challenge.holanda.ms.popular.home.domain.exceptions.InvalidNameException;
import com.digix.challenge.holanda.ms.popular.home.domain.exceptions.ValidationException;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;
import java.util.UUID;
import java.util.regex.Pattern;

@Data
public class Locale extends Entity {
    @NonNull
    private String name;

    @NonNull
    private Integer code;

    public Locale() {

    }

    public Locale(@NonNull String name, @NonNull Integer code) {
        this.name = name;
        this.code = code;
    }

    public Locale(@NonNull String name, @NonNull Integer code, String id, boolean active, Date createdAt, Date updatedAt) {
        super(id, active, createdAt, updatedAt);

        this.name = name;
        this.code = code;
    }

    @Override
    public void validate() throws ValidationException {
        if (!Pattern.compile("[A-Z][a-z]*").matcher(this.name).matches()) {
            throw new InvalidNameException();
        }

        if (this.code < 0) {
            throw new InvalidCodeException();
        }
    }
}
