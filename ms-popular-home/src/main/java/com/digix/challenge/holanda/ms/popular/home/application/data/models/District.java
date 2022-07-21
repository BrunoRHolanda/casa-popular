package com.digix.challenge.holanda.ms.popular.home.application.data.models;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.util.HashSet;
import java.util.Set;

@Data
public class District extends Model {
    @NonNull
    private String name;

    @NonNull
    private Integer code;

    @NonNull
    private String cityId;
}
