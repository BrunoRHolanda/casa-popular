package com.digix.challenge.holanda.ms.popular.home.domain.valueobjects;

public interface ValueObject<T> {
    public boolean validate();
    public T make();
    public String toString();
}
