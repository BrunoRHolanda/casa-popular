package com.digix.challenge.holanda.ms.popular.home.domain.exceptions;

public class UnrelatedRuleException extends ValidationException {
    public UnrelatedRuleException() {
        super("Esta seleção não possui a regra informada", 109);
    }
}
