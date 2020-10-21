package com.bank.account.model;

import java.util.Objects;

public class AccountNumber {
    private String number;

    public AccountNumber(String value) {
        validate(value);
        this.number = value;
    }

    private void validate(String value) {
        if ((value == null) || (value != null && value.length() != 6)) {
            throw new IllegalArgumentException("wrong number account");
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.number);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof AccountNumber))
            return false;
        AccountNumber account = (AccountNumber) o;
        return account.number == this.number;
    }
}
