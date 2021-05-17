package com.bank.account.fake;

import com.bank.account.model.contract.Repository;

import java.util.Hashtable;

import com.bank.account.model.Account;

public class MemoryRepositoryAccount implements Repository<Account, String> {

    private Hashtable<String, Account> dict;

    public MemoryRepositoryAccount() {
        dict = new Hashtable<String, Account>();
    }

    @Override
    public Account get(String field) {
        return dict.get(field);
    }

    @Override
    public void add(Account entity) {
        dict.put(entity.getAccountNumber(), entity);
    }
}