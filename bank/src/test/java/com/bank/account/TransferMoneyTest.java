package com.bank.account;

import com.bank.account.model.Account;
import com.bank.account.model.AccountNumber;
import com.bank.account.model.service.TransferMoney;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferMoneyTest {
    
    @Test
    public void testTransferMoney() throws Exception {
        Account accountFrom = new Account(new AccountNumber("123456"), 5000.0);
        Account accountTo = new Account(new AccountNumber("654321"), 5000.0);

        TransferMoney transferMoney = new TransferMoney();
        transferMoney.transfer(accountFrom, accountTo, 100.0);

        assertEquals(4900.0, accountFrom.getBalance());
        assertEquals(5100.0, accountTo.getBalance());
    }
}
