package com.digix.challenge.holanda.ms.popular.home.domain.entities;

import com.digix.challenge.holanda.ms.popular.home.domain.exceptions.InvalidCodeException;
import com.digix.challenge.holanda.ms.popular.home.domain.exceptions.InvalidNameException;
import com.digix.challenge.holanda.ms.popular.home.domain.exceptions.ValidationException;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.regex.Pattern;

@Data
@RequiredArgsConstructor
public class City implements Entity {

    @NonNull
    private State state;

    @NonNull
    private String name;

    @NonNull
    private Integer code;

    @Override
    public void validate() throws ValidationException {
//        if (!Pattern.compile("[A-Z][a-z]+").matcher(this.name).matches()) {
//            throw new InvalidNameException();
//        }

        if (this.code < 0) {
            throw new InvalidCodeException();
        }

        this.state.validate();
    }
}
