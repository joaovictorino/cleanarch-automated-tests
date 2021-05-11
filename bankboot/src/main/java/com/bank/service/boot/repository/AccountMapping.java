package com.bank.service.boot.repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="account")
public class AccountMapping {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String number;
    private double balance;

    public AccountMapping() {
    }

    public AccountMapping(String number, double balance) {
        this.number = number;
        this.balance = balance;
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
}
