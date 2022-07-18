package com.digix.challenge.holanda.ms.popular.home.domain.exceptions;

public class InvalidCodeException extends ValidationException {
    public InvalidCodeException() {
        super("O campo código é inválido", 106);
    }
}
