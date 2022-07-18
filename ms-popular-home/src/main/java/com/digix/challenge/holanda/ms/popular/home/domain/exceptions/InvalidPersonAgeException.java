package com.digix.challenge.holanda.ms.popular.home.domain.exceptions;

public class InvalidPersonAgeException extends ValidationException {
    public InvalidPersonAgeException() {
        super("O campo idade é inválido", 105);
    }
}
