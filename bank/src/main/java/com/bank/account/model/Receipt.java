package com.bank.account.model;

import java.util.Random;

public class Receipt {

    private String transactionId;

    public Receipt() {
        this.transactionId = generateId();
    }

    public String generateId() {
        Random random = new Random();
        return random.ints(100000, 999999).findFirst().toString();
    }

    public String getTransactionId() {
        return this.transactionId;
    }
}
