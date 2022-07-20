package com.digix.challenge.holanda.ms.popular.home.domain.entities;

import com.digix.challenge.holanda.ms.popular.home.domain.exceptions.InvalidZipCodeException;
import com.digix.challenge.holanda.ms.popular.home.domain.exceptions.ValidationException;
import com.digix.challenge.holanda.ms.popular.home.domain.valueobjects.ZipCode;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;
import java.util.UUID;

@Data
public class Address extends Entity {

    @NonNull
    private District district;

    @NonNull
    private String street;

    private Integer number;

    @NonNull
    private ZipCode zipCode;

    private String complement;

    public Address() {

    }

    public Address(@NonNull District district,  @NonNull String street,  @NonNull ZipCode zipCode) {
        this.district = district;
        this.street = street;
        this.zipCode = zipCode;
    }

    public Address(@NonNull District district, @NonNull String street, @NonNull Integer number, @NonNull ZipCode zipCode, String id, boolean active, Date createdAt, Date updatedAt) {
        super(id, active, createdAt, updatedAt);

        this.district = district;
        this.street = street;
        this.number = number;
        this.zipCode = zipCode;
    }

    @Override
    public void validate() throws ValidationException {
        this.district.validate();

        if (!this.zipCode.validate()) {
            throw new InvalidZipCodeException();
        }
    }
}
