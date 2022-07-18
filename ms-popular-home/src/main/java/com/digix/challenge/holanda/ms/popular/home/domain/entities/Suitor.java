package com.digix.challenge.holanda.ms.popular.home.domain.entities;

import com.digix.challenge.holanda.ms.popular.home.domain.valueobjects.Cpf;
import com.digix.challenge.holanda.ms.popular.home.domain.valueobjects.Email;
import com.digix.challenge.holanda.ms.popular.home.domain.valueobjects.Phone;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class Suitor extends Person {
    @NonNull
    private Phone phone;
    @NonNull
    private Email email;

    public Suitor(@NonNull Phone phone, @NonNull Email email, String name, int age, Cpf cpf) {
        super(name, age, cpf);

        this.phone = phone;
        this.email = email;
    }
}
