package com.digix.challenge.holanda.ms.popular.home.domain.entities;

import com.digix.challenge.holanda.ms.popular.home.domain.exceptions.InvalidCpfException;
import com.digix.challenge.holanda.ms.popular.home.domain.exceptions.InvalidPersonAgeException;
import com.digix.challenge.holanda.ms.popular.home.domain.exceptions.InvalidNameException;
import com.digix.challenge.holanda.ms.popular.home.domain.exceptions.ValidationException;
import com.digix.challenge.holanda.ms.popular.home.domain.valueobjects.Cpf;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;
import java.util.UUID;
import java.util.regex.Pattern;

@Data
public class Person extends Entity {
    @NonNull
    private String name;
    @NonNull
    private Integer age;

    private Cpf cpf;

    public Person() {

    }

    public Person(@NonNull String name, @NonNull Integer age) {
        this.name = name;
        this.age = age;
    }

    public Person(@NonNull String name, @NonNull Integer age, @NonNull Cpf cpf, UUID id, boolean active, Date createdAt, Date updatedAt) {
        super(id, active, createdAt, updatedAt);

        this.name = name;
        this.age = age;
        this.cpf = cpf;
    }

    @Override
    public void validate() throws ValidationException {
        if (this.cpf != null) {
            if (!this.cpf.validate()) {
                throw new InvalidCpfException();
            }
        }

        if (!Pattern.compile("[A-Z][a-z]* [A-Z][a-z]*").matcher(this.name).matches()) {
            throw new InvalidNameException();
        }

        if (this.age < 1) {
            throw new InvalidPersonAgeException();
        }
    }
}
