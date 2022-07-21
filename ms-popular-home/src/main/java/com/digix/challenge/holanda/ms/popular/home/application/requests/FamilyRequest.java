package com.digix.challenge.holanda.ms.popular.home.application.requests;

import lombok.Data;
import lombok.NonNull;

@Data
public class FamilyRequest {
    private AddressRequest address;
    private SpouseRequest spouse;
    private SuitorRequest suitor;
    private PersonRequest[] dependents;
    private Float income;

    @Data
    public static class AddressRequest {
        private String districtId;
        private String street;
        private Integer number;
        private String zipCode;
        private String complement;
    }

    @Data
    public static class PersonRequest {
        private String name;
        private Integer age;
        private String cpf;
    }

    @Data
    public static class SpouseRequest {
        private PersonRequest person;
    }

    @Data
    public static class SuitorRequest {
        private String email;
        private String phone;
        private PersonRequest person;
    }
}
