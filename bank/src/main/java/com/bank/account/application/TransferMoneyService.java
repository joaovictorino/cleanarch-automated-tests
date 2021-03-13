package com.bank.account.application;

import com.bank.account.application.dto.TransferDTO;
import com.bank.account.model.Account;
import com.bank.account.model.AccountNumber;
import com.bank.account.model.Receipt;
import com.bank.account.model.contract.Repository;
import com.bank.account.model.service.TransferMoney;

public class TransferMoneyService {

    private Repository<Account, AccountNumber> repository;

    public TransferMoneyService(Repository<Account, AccountNumber> repository) {
        this.repository = repository;
    }

    public String transfer(TransferDTO dto) {
        Account accountFrom = repository.get(new AccountNumber(dto.getAccountFrom()));
        Account accountTo = repository.get(new AccountNumber(dto.getAccountTo()));

        if (accountFrom == null)
            throw new IllegalArgumentException("account from not found ");

        if (accountTo == null)
            throw new IllegalArgumentException("account to not found");

        TransferMoney transfer = new TransferMoney();
        Receipt receipt = transfer.transfer(accountFrom, accountTo, dto.getValue());

        repository.add(accountFrom);
        repository.add(accountTo);

        return receipt.getTransactionId();
    }
}
