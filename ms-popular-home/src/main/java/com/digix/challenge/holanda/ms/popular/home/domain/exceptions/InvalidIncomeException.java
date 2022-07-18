package com.digix.challenge.holanda.ms.popular.home.domain.exceptions;

public class InvalidIncomeException extends ValidationException {
    public InvalidIncomeException() {
        super("A renda informada é inválida", 108);
    }
}
