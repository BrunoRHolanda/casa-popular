package com.digix.challenge.holanda.ms.popular.home.application.data.models;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.relational.core.mapping.MappedCollection;

@Data
public class Suitor extends Model {
    @NonNull
    private String email;

    @NonNull
    private String phone;

    @NonNull
    private String personId;
}
