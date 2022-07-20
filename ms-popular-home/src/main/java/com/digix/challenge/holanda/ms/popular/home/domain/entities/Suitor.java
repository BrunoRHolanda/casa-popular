package com.digix.challenge.holanda.ms.popular.home.domain.entities;

import com.digix.challenge.holanda.ms.popular.home.domain.exceptions.InvalidEmailException;
import com.digix.challenge.holanda.ms.popular.home.domain.exceptions.InvalidPhoneNumberException;
import com.digix.challenge.holanda.ms.popular.home.domain.exceptions.ValidationException;
import com.digix.challenge.holanda.ms.popular.home.domain.valueobjects.Cpf;
import com.digix.challenge.holanda.ms.popular.home.domain.valueobjects.Email;
import com.digix.challenge.holanda.ms.popular.home.domain.valueobjects.Phone;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class Suitor extends Person {
    @NonNull
    private Phone phone;
    @NonNull
    private Email email;

    public Suitor() {
        super();
    }

    public Suitor(@NonNull Phone phone, @NonNull Email email, String name, int age, Cpf cpf) {
        super(name, age);

        this.phone = phone;
        this.email = email;

        this.setCpf(cpf);
    }

    public Suitor(String id, boolean active, Date createdAt, Date updatedAt, @NonNull String phone, @NonNull String email, String name, int age, String cpf) {
        super(name, age, cpf, id, active, createdAt, updatedAt);

        this.phone = new Phone(phone);
        this.email = new Email(email);
    }

    @Override
    public void validate() throws ValidationException {
        super.validate();

        if (!this.email.validate()) {
            throw new InvalidEmailException();
        }

        if (!this.phone.validate()) {
            throw new InvalidPhoneNumberException();
        }
    }
}
