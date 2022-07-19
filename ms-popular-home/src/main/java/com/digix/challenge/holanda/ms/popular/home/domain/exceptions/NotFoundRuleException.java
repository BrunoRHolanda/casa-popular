package com.digix.challenge.holanda.ms.popular.home.domain.exceptions;

public class NotFoundRuleException extends BusinessLogicException {
    public NotFoundRuleException() {
        super("Regra não cadastrada!", 110);
    }
}
