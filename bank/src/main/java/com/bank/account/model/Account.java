package com.bank.account.model;

public class Account {
    private AccountNumber accountNumber;
    private double balance;

    public Account(String accountNumber, double balance) {
        this.accountNumber = new AccountNumber(accountNumber);
        this.balance = balance;
    }

    public void withDraw(double value) {
        validateValue(value);

        if ((this.balance - value) < 0) {
            throw new IllegalArgumentException("no balance available");
        }
        this.balance -= value;
    }

    public void deposit(double value) {
        validateValue(value);

        this.balance += value;
    }

    public double getBalance() {
        return this.balance;
    }

    public String getAccountNumber() {
        return this.accountNumber.getNumber();
    }

    private void validateValue(double value) {
        if(value <= 0) {
            throw new IllegalArgumentException("value should be greater than zero");
        }
    }
}
