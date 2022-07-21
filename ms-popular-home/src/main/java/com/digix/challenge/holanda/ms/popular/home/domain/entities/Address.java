package com.digix.challenge.holanda.ms.popular.home.domain.entities;

import com.digix.challenge.holanda.ms.popular.home.domain.exceptions.InvalidZipCodeException;
import com.digix.challenge.holanda.ms.popular.home.domain.exceptions.ValidationException;
import com.digix.challenge.holanda.ms.popular.home.domain.valueobjects.ZipCode;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@RequiredArgsConstructor
public class Address implements Entity {

    @NonNull
    private District district;

    @NonNull
    private String street;

    private Integer number;

    @NonNull
    private ZipCode zipCode;

    private String complement;

    @Override
    public void validate() throws ValidationException {
        this.district.validate();

        if (!this.zipCode.validate()) {
            throw new InvalidZipCodeException();
        }
    }
}
