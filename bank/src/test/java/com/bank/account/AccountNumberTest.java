package com.bank.account;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import com.bank.account.model.AccountNumber;

public class AccountNumberTest {
    @Test
    public void testAccountNumberSuccess() {
        AccountNumber number = new AccountNumber("123456");
    }

    @Test
    public void testAccountNumberFailure() {
        assertThrows(IllegalArgumentException.class, () -> {
            AccountNumber number = new AccountNumber("3456");
        });
    }

    @Test
    public void testAccountNumberFailureNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            AccountNumber number = new AccountNumber(null);
        });
    }

    @Test
    public void testAccountNumberFailureEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            AccountNumber number = new AccountNumber("");
        });
    }

    @Test
    public void testAccountNumberFailureOverLimit() {
        assertThrows(IllegalArgumentException.class, () -> {
            AccountNumber number = new AccountNumber("1234567");
        });
    }
}
