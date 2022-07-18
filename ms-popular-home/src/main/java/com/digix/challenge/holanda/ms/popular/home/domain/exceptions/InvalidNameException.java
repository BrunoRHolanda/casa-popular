package com.digix.challenge.holanda.ms.popular.home.domain.exceptions;

public class InvalidNameException extends ValidationException {
    public InvalidNameException() {
        super("O campo nome é inválido", 104);
    }
}
