package com.bank.account.model;

import java.util.Objects;

public class AccountNumber {
    private String number;

    public AccountNumber(String value) {
        validate(value);
        this.number = value;
    }

    private void validate(String value) {
        if ((value == null) 
            || (value != null 
                && value.length() != 6)) {
            throw new IllegalArgumentException("wrong number account");
        }
    }

    public String getNumber() {
        return this.number;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                    + ((number == null) ? 0 : number.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null || !(obj instanceof AccountNumber))
            return false;
        AccountNumber account = (AccountNumber) obj;
        return account.number == this.number;
    }
}
