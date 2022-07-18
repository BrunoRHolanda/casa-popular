package com.digix.challenge.holanda.ms.popular.home.domain.exceptions;

public class InvalidPhoneNumberException extends ValidationException {
    public InvalidPhoneNumberException() {
        super("O Número de telefone informado é inválido", 102);
    }
}
