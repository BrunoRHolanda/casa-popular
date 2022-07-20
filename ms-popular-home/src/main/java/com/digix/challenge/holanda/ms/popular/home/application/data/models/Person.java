package com.digix.challenge.holanda.ms.popular.home.application.data.models;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.util.Date;
import java.util.Set;

@Data
public class Person extends Model{

    @NonNull
    private String name;

    @NonNull
    private Integer age;

    private String cpf;
}
