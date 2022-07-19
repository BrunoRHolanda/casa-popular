package com.digix.challenge.holanda.ms.popular.home.domain.exceptions;

public abstract class ValidationException extends BusinessLogicException {
    public ValidationException(String message, int code) {
        super(message, code);
    }
}
