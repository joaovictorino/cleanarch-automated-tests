package com.bank.account.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Objects;

import org.junit.jupiter.api.Test;  

public class AccountNumberTest {
    @Test
    public void testAccountNumber() {
        AccountNumber accountNumber = new AccountNumber("123456");
        assertEquals("123456", accountNumber.getNumber());
        assertEquals(6, accountNumber.getNumber().length());
    }

    @Test
    public void testAccountNumberHashCodeEquals() {
        AccountNumber accountNumber = new AccountNumber("123456");
        AccountNumber accountNumber2 = new AccountNumber("123456");
        assertEquals(accountNumber.hashCode(), accountNumber2.hashCode());
        assertEquals(accountNumber, accountNumber2);
        assertEquals(accountNumber, accountNumber);
    }

    @Test
    public void testAccountNumberHashCodeEqualsFailure() {
        AccountNumber accountNumber = new AccountNumber("123456");
        AccountNumber accountNumber2 = new AccountNumber("654321");
        assertNotEquals(accountNumber.hashCode(), accountNumber2.hashCode());
        assertNotEquals(accountNumber, accountNumber2);
        assertNotEquals(accountNumber, null);
    }

    @Test
    public void testAccountNumberHashCodeSize() {
        AccountNumber accountNumber = new AccountNumber("123456");
        assertEquals(accountNumber.hashCode(), Objects.hash("123456"));
    }

    @Test
    public void testAccountNumberFailure() {
        assertThrows(IllegalArgumentException.class, () -> {
            new AccountNumber("3456");
        });
    }

    @Test
    public void testAccountNumberFailureNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new AccountNumber(null);
        });
    }

    @Test
    public void testAccountNumberFailureEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            new AccountNumber("");
        });
    }

    @Test
    public void testAccountNumberFailureOverLimit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new AccountNumber("1234567");
        });
    }
}
