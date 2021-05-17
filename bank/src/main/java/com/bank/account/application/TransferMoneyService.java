package com.bank.account.application;

import com.bank.account.application.dto.TransferDTO;
import com.bank.account.model.Account;
import com.bank.account.model.Receipt;
import com.bank.account.model.contract.Repository;
import com.bank.account.model.service.TransferMoney;

public class TransferMoneyService {

    private Repository<Account, String> repository;

    public TransferMoneyService(Repository<Account, String> repository) {
        this.repository = repository;
    }

    public String transfer(TransferDTO dto) {
        Account accountFrom = repository.get(dto.getAccountFrom());
        Account accountTo = repository.get(dto.getAccountTo());

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
