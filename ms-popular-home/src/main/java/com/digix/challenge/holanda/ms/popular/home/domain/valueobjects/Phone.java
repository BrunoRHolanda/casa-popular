package com.digix.challenge.holanda.ms.popular.home.domain.valueobjects;

public class Phone implements ValueObject<String> {
    private final String phoneNumber;

    public Phone(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean validate() {
        try {
            int countryCode = Integer.parseInt(this.phoneNumber.substring(0, 1));
            int areaCode = Integer.parseInt(this.phoneNumber.substring(2, 3));
            int number = Integer.parseInt(this.phoneNumber.substring(4, 12));

            return countryCode > 0 && areaCode > 0 && number > 0;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String make() {
        return this.phoneNumber;
    }
}
