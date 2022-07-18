package com.digix.challenge.holanda.ms.popular.home.domain.entities;

import com.digix.challenge.holanda.ms.popular.home.domain.valueobjects.Cpf;
import lombok.Data;
import lombok.NonNull;

@Data
public class Person extends Entity {
    @NonNull
    protected String name;
    @NonNull
    protected int age;
    @NonNull
    protected Cpf cpf;
}
