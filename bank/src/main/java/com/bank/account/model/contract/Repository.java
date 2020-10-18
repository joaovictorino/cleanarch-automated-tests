package com.bank.account.model.contract;

public interface Repository<T, I> {
    T get(I field);
    void add(T entity);
}
