package com.digix.challenge.holanda.ms.popular.home.application.usecases;

public interface UseCase<T, K> {
    public K handle(T request);
}
