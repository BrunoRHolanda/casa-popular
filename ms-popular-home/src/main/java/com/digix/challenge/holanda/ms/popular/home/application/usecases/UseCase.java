package com.digix.challenge.holanda.ms.popular.home.application.usecases;

import com.digix.challenge.holanda.ms.popular.home.domain.exceptions.ValidationException;

public interface UseCase<T, K> {
    public K handle(T request) throws ValidationException;
}
