package com.digix.challenge.holanda.ms.popular.home.domain.exceptions;

public class InvalidEmailException extends ValidationException {
    public InvalidEmailException() {
        super("Email é inválido", 101);
    }
}
