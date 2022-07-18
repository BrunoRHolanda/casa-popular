package com.digix.challenge.holanda.ms.popular.home.domain.exceptions;

public abstract class ValidationException extends Exception {

    private int code;

    ValidationException(String message, int code) {
        super(message);
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return "Erro [" + this.getCode() + "]: " + super.getMessage();
    }
}
