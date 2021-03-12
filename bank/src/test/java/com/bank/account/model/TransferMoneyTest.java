package com.bank.account.model;

import com.bank.account.model.service.TransferMoney;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TransferMoneyTest {
    
    @Test
    public void testTransferMoneySuccess() {
        Account accountFrom = new Account(new AccountNumber("123456"), 5000.0);
        Account accountTo = new Account(new AccountNumber("654321"), 5000.0);

        TransferMoney transferMoney = new TransferMoney();
        transferMoney.transfer(accountFrom, accountTo, 100.0);

        assertEquals(4900.0, accountFrom.getBalance());
        assertEquals(5100.0, accountTo.getBalance());
    }

    @Test
    public void testTransferMoneyFailureAccountNull() {
        Account accountFrom = new Account(new AccountNumber("123456"), 5000.0);

        TransferMoney transferMoney = new TransferMoney();

        assertThrows(IllegalArgumentException.class, () -> {
            transferMoney.transfer(accountFrom, null, 100.0);
        });
    }
}
