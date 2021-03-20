package com.bank.account.application;

import com.bank.account.model.Account;
import com.bank.account.model.AccountNumber;
import com.bank.account.model.contract.Repository;
import com.bank.account.application.dto.TransferDTO;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class TransferMoneyServiceMockitoTest {

    @Test
    public void testTransferMoneyServiceSuccess() {
        Account accountFrom = new Account(new AccountNumber("123456"), 5000.0);
        Account accountTo = new Account(new AccountNumber("654321"), 5000.0);

        Repository<Account, AccountNumber> repo = createRepositoryAccount(accountFrom, accountTo);

        TransferMoneyService appService = new TransferMoneyService(repo);
        
        TransferDTO dto = new TransferDTO();
        dto.setAccountFrom("123456");
        dto.setAccountTo("654321");
        dto.setValue(100.0);

        appService.transfer(dto);

        assertAll("all results",
            () -> verify(repo, times(1)).get(new AccountNumber("123456")),
            () -> verify(repo, times(1)).get(new AccountNumber("654321")),
            () -> verify(repo, times(1)).add(accountFrom),
            () -> verify(repo, times(1)).add(accountTo),
            () -> assertEquals(4900.0, repo.get(new AccountNumber("123456")).getBalance()),
            () -> assertEquals(5100.0, repo.get(new AccountNumber("654321")).getBalance())
        );
    }

    @Test
    public void testTransferMoneyServiceFailureAccountNotFound() {
        Account accountFrom = new Account(new AccountNumber("123456"), 5000.0);
        Account accountTo = new Account(new AccountNumber("654321"), 5000.0);

        Repository<Account, AccountNumber> repo = createRepositoryAccount(accountFrom, accountTo);

        TransferMoneyService appService = new TransferMoneyService(repo);
        
        TransferDTO dto = new TransferDTO();
        dto.setAccountFrom("654321");
        dto.setAccountTo("444444");
        dto.setValue(100.0);

        assertThrows(IllegalArgumentException.class, () -> {
            appService.transfer(dto);
        });
    }

    @SuppressWarnings("unchecked")
    private Repository<Account, AccountNumber> createRepositoryAccount(Account accountFrom, Account accountTo) {
        Repository<Account, AccountNumber> repository = (Repository<Account, AccountNumber>) mock(Repository.class);
        when(repository.get(new AccountNumber("123456"))).thenReturn(accountFrom);
        when(repository.get(new AccountNumber("654321"))).thenReturn(accountTo);
        return repository;
    }
}
