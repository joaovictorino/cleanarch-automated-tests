package com.bank.account.model;

import java.util.Objects;

public class AccountNumber {
    private String number;

    public AccountNumber(String number) {
        validate(number);
        this.number = number;
    }

    private void validate(String number) {
        if (number == null
            || IsNot6NumericDigits(number)) {
            throw new IllegalArgumentException("wrong number account");
        }
    }

    private boolean IsNot6NumericDigits(String number) {
        return !number.matches("^[0-9]{6}$");
    }

    public String getNumber() {
        return this.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof AccountNumber))
            return false;
        AccountNumber account = (AccountNumber) obj;
        return account.number == this.number;
    }
}
