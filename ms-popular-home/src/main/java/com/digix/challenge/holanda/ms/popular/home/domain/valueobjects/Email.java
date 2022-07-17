package com.digix.challenge.holanda.ms.popular.home.domain.valueobjects;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email implements ValueObject<String> {
    private final String email;

    public Email(String email) {
        this.email = email;
    }

    @Override
    public boolean validate() {
        try {
            Pattern pattern = Pattern.compile("^(.+)@(.+)$");

            Matcher matcher = pattern.matcher(this.email);

            return matcher.matches();
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String make() {
        return this.email;
    }
}
