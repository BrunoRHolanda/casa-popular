package com.digix.challenge.holanda.ms.popular.home.domain.exceptions;

public class UnrelatedDependentException extends ValidationException {
    public UnrelatedDependentException() {
        super("Dependente não relacionado com esta familia", 107);
    }
}
