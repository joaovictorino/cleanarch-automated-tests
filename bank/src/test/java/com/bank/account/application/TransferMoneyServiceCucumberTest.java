package com.bank.account.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import com.bank.account.application.dto.TransferDTO;
import com.bank.account.model.Account;
import com.bank.account.model.AccountNumber;
import com.bank.account.model.contract.Repository;

public class TransferMoneyServiceCucumberTest {
    private Account accountFrom;
    private Account accountTo;
    private Repository<Account, AccountNumber> repo;
    private TransferMoneyService appService;
    private TransferDTO dto;

    @Given("account {string} with balance {double} and account {string} with balance {double}")
    public void createTwoAccounts(String numberFrom, Double balanceFrom, String numberTo, Double balanceTo) {
        accountFrom = new Account(new AccountNumber(numberFrom), balanceFrom);
        accountTo = new Account(new AccountNumber(numberTo), balanceTo);
        repo = createRepositoryAccount(accountFrom, accountTo);
        appService = new TransferMoneyService(repo);
        
        dto = new TransferDTO();
        dto.setAccountFrom(numberFrom);
        dto.setAccountTo(numberTo);
    }
    
    @When("account {string} transfer {double} to account {string}")
    public void transferMoney(String from, Double value, String to) {
        dto.setValue(value);
        appService.transfer(dto);
    }

    @Then("the balance of account {string} should be {double} and account {string} {double}")
    public void assertBalance(String numberFrom, Double balanceFrom, String numberTo, Double balanceTo) {
        assertEquals(balanceFrom, repo.get(accountFrom.getAccountNumber()).getBalance());
        assertEquals(balanceTo, repo.get(accountTo.getAccountNumber()).getBalance());
    }

    @SuppressWarnings("unchecked")
    private Repository<Account, AccountNumber> createRepositoryAccount(Account accountFrom, Account accountTo) {
        Repository<Account, AccountNumber> repository = (Repository<Account, AccountNumber>) mock(Repository.class);
        when(repository.get(accountFrom.getAccountNumber())).thenReturn(accountFrom);
        when(repository.get(accountTo.getAccountNumber())).thenReturn(accountTo);
        return repository;
    }
}
