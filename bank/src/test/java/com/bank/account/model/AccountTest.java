package com.bank.account.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class AccountTest {

    @Test
    public void testWithDrawAccount() {
        Account account = new Account(new AccountNumber("123456"), 5000.0);
        account.withDraw(200.0);
        assertEquals(account.getBalance(), 4800.0);
    }

    @Test
    public void testWithDrawAccountToZeroBalance() {
        Account account = new Account(new AccountNumber("123456"), 5000.0);
        account.withDraw(5000.0);
        assertEquals(account.getBalance(), 0.0);
    }

    @Test
    public void testWithDrawAccountFailureReachUnderLimit() {
        Account account = new Account(new AccountNumber("123456"), 199.0);
        assertThrows(IllegalArgumentException.class, () -> {
            account.withDraw(200.0);
        });
    }

    @Test
    public void testWithDrawAccountFailureNoValue() {
        Account account = new Account(new AccountNumber("123456"), 199.0);
        assertThrows(IllegalArgumentException.class, () -> {
            account.withDraw(0.0);
        });
    }

    @Test
    public void testDepositAccount() {
        Account account = new Account(new AccountNumber("123456"), 5000.0);
        account.deposit(200.0);
        assertEquals(account.getBalance(), 5200.0);
    }

    @Test
    public void testWithDrawAccountFailureValueUnderZero() {
        Account account = new Account(new AccountNumber("123456"), 200.0);
        assertThrows(IllegalArgumentException.class, () -> {
            account.withDraw(-5.0);
        });
    }

    @Test
    public void testDepositAccountFailureValueUnderZero() {
        Account account = new Account(new AccountNumber("123456"), 200.0);
        assertThrows(IllegalArgumentException.class, () -> {
            account.deposit(-5.0);
        });
    }
}