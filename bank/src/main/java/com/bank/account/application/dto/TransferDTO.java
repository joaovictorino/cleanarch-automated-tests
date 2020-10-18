package com.bank.account.application.dto;

public class TransferDTO {
    private String accountFrom;
    private String accountTo;
    private double value;

    public void setAccountFrom(String accountFrom) {
        this.accountFrom = accountFrom;
    }

    public void setAccountTo(String accountTo) {
        this.accountTo = accountTo;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getAccountFrom() {
        return this.accountFrom;
    }

    public String getAccountTo() {
        return this.accountTo;
    }

    public double getValue() {
        return this.value;
    }
}
