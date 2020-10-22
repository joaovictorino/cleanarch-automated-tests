package com.bank.account.model;

public class Account {
    private AccountNumber accountNumber;
    private double balance;

    public Account(AccountNumber accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void withDraw(double value) throws Exception {
        if(value < 0) {
            throw new Exception("value should be greater than zero");
        }

        if ((this.balance - value) < 0) {
            throw new Exception("no balance available");
        }
        this.balance -= value;
    }

    public void deposit(double value) throws Exception {
        if(value < 0) {
            throw new Exception("value should be greater than zero");
        }

        this.balance += value;
    }

    public double getBalance() {
        return this.balance;
    }

    public AccountNumber getAccountNumber() {
        return this.accountNumber;
    }
}
