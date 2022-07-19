package com.digix.challenge.holanda.ms.popular.home.domain.entities;

import com.digix.challenge.holanda.ms.popular.home.domain.valueobjects.Cpf;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class Spouse  extends Person {
    public Spouse() {
        super();
    }

    public Spouse(String name, int age, Cpf cpf) {
        super(name, age);

        this.setCpf(cpf);
    }
}
