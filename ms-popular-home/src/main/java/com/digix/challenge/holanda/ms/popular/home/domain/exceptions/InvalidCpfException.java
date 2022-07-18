package com.digix.challenge.holanda.ms.popular.home.domain.exceptions;

public class InvalidCpfException extends ValidationException {
    public InvalidCpfException() {
        super("CPF é inválido", 100);
    }
}
