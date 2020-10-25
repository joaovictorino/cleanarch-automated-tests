package com.bank.service.quarkus.repository;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

public class AccountMapping {

    private ObjectId id;

    @BsonProperty(value="number")
    private String number;

    @BsonProperty(value="balance")
    private double balance;

    public AccountMapping() {
    }

    public AccountMapping(String number, double balance) {
        this.number = number;
        this.balance = balance;
    }

    public ObjectId getId() {
        return this.id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
    
    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}