package com.digix.challenge.holanda.ms.popular.home.application.data.models;

import lombok.Data;
import lombok.NonNull;

@Data
public class Address extends Model {
    @NonNull
    private String districtId;
    @NonNull
    private String street;
    private Integer number;
    @NonNull
    private String zipCode;
    private String complement;
}
