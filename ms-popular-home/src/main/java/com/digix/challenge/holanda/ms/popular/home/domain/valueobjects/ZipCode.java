package com.digix.challenge.holanda.ms.popular.home.domain.valueobjects;

import lombok.Getter;

@Getter
public class ZipCode implements ValueObject<String> {
    private final String zipCode;

    public ZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public boolean validate() {
        return this.zipCode.length() == 8;
    }

    @Override
    public String make() {
        return this.zipCode;
    }

    public String toString() {
        return this.make();
    }
}
