package com.bank.account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import com.bank.account.model.Account;
import com.bank.account.model.AccountNumber;

public class AccountTest {

    @Test
    public void testWithDrawAccount() throws Exception {
        Account account = new Account(new AccountNumber("123456"), 5000.0);
        account.withDraw(200.0);
        assertEquals(account.getBalance(), 4800.0);
    }

    @Test
    public void testWithDrawAccountFailureReachZero() throws Exception {
        Account account = new Account(new AccountNumber("123456"), 199.0);
        assertThrows(Exception.class, () -> {
            account.withDraw(200.0);
        });
    }

    @Test
    public void testDepositAccount() throws Exception {
        Account account = new Account(new AccountNumber("123456"), 5000.0);
        account.deposit(200.0);
        assertEquals(account.getBalance(), 5200.0);
    }

    @Test
    public void testWithDrawAccountFailureValueUnderZero() throws Exception {
        Account account = new Account(new AccountNumber("123456"), 200.0);
        assertThrows(Exception.class, () -> {
            account.withDraw(-5.0);
        });
    }

    @Test
    public void testDepositAccountFailureValueUnderZero() throws Exception {
        Account account = new Account(new AccountNumber("123456"), 200.0);
        assertThrows(Exception.class, () -> {
            account.deposit(-5.0);
        });
    }
}