package com.bank.account.model.service;

import com.bank.account.model.Account;
import com.bank.account.model.Receipt;

public class TransferMoney {
    public Receipt transfer(Account accountFrom, Account accountTo, double value) throws Exception {
        accountFrom.withDraw(value);
        accountTo.deposit(value);
        return new Receipt();
    }
}
