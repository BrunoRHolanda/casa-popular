package com.digix.challenge.holanda.ms.popular.home.domain.valueobjects;

import lombok.Getter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
public class Email implements ValueObject<String> {
    private final String email;

    public Email(String email) {
        this.email = email;
    }

    @Override
    public boolean validate() {
        try {
            return Pattern
                    .compile("^(.+)@(.+)$")
                    .matcher(this.email)
                    .matches();
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String make() {
        return this.email;
    }

    public String toString() {
        return this.make();
    }
}
