package com.bank.account.model.service;

import com.bank.account.model.Account;

public class TransferMoney {
    public void transfer(Account accountFrom, Account accountTo, double value) {
        try {
            accountFrom.withDraw(value);
            accountTo.deposit(value);
        } catch(Exception ex) {
            
        }
    }
}
