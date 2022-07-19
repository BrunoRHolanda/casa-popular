package com.digix.challenge.holanda.ms.popular.home.domain.entities.builders;

import com.digix.challenge.holanda.ms.popular.home.domain.entities.*;
import com.digix.challenge.holanda.ms.popular.home.domain.exceptions.ValidationException;
import com.digix.challenge.holanda.ms.popular.home.domain.valueobjects.Cpf;
import com.digix.challenge.holanda.ms.popular.home.domain.valueobjects.Email;
import com.digix.challenge.holanda.ms.popular.home.domain.valueobjects.Phone;
import com.digix.challenge.holanda.ms.popular.home.domain.valueobjects.ZipCode;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.Map;

public class FamilyBuilder {
    private Suitor suitor;
    private Spouse spouse;
    private ArrayList<Person> dependents;
    private Address address;
    private float income;

    private FamilyBuilder() {}

    public FamilyBuilder begin() {
        return new FamilyBuilder();
    }

    public FamilyBuilder createSuitor(String phone, String email, String name, int age, String cpf) throws ValidationException {
        this.suitor = new Suitor(new Phone(phone), new Email(email), name, age, new Cpf(cpf));

        this.suitor.validate();

        return this;
    }

    public FamilyBuilder createSpouse(String name, int age, String cpf) throws ValidationException {
        this.spouse = new Spouse(name, age, new Cpf(cpf));

        this.spouse.validate();

        return this;
    }

    public FamilyBuilder addDependent(String name, int age, String cpf) throws ValidationException {
        if (this.dependents == null) {
            this.dependents = new ArrayList<>();
        }

        Person p = new Person(name, age);

        if (cpf != null) {
            p.setCpf(new Cpf(cpf));
        }

        p.validate();

        this.dependents.add(p);

        return this;
    }

    public FamilyBuilder createAddress(District district, String street, String zipCode) throws ValidationException {
        this.address = new Address(district, street, new ZipCode(zipCode));

        this.address.validate();

        return this;
    }

    public FamilyBuilder setIncome(float income) {
        this.income = income;

        return this;
    }

    public Family build() throws ValidationException {
        Family f = new Family(this.suitor, this.spouse, this.address, this.income);

        if (this.dependents != null) {
            for (Person dependent : this.dependents) {
                f.addDependent(dependent);
            }
        }

        return f;
    }
}
