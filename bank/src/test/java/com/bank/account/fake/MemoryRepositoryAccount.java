package com.bank.account.fake;

import com.bank.account.model.contract.Repository;

import java.util.Hashtable;

import com.bank.account.model.Account;
import com.bank.account.model.AccountNumber;

public class MemoryRepositoryAccount implements Repository<Account, AccountNumber> {

    private Hashtable<AccountNumber, Account> dict;

    public MemoryRepositoryAccount() {
        dict = new Hashtable<AccountNumber, Account>();
    }

    @Override
    public Account get(AccountNumber field) {
        return dict.get(field);
    }

    @Override
    public void add(Account entity) {
        dict.put(entity.getAccountNumber(), entity);
    }
}