package com.digix.challenge.holanda.ms.popular.home.domain.exceptions;

public class InvalidZipCodeException extends ValidationException {
    public InvalidZipCodeException() {
        super("O CEP informado é inválido", 103);
    }
}
