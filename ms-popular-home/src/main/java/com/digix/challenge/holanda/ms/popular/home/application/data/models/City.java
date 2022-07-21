package com.digix.challenge.holanda.ms.popular.home.application.data.models;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.util.HashSet;
import java.util.Set;

@Data
public class City extends Model {
    @NonNull
    private String name;

    @NonNull
    private Integer code;

    @NonNull
    private String stateId;
}
