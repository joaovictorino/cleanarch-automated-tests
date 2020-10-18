package com.bank.account.application;

import com.bank.account.application.dto.TransferDTO;
import com.bank.account.model.Account;
import com.bank.account.model.AccountNumber;
import com.bank.account.model.contract.Repository;
import com.bank.account.model.service.TransferMoney;

public class TransferMoneyService {

    private Repository<Account, AccountNumber> repository;

    public TransferMoneyService(Repository<Account, AccountNumber> repository) {
        this.repository = repository;
    }

    public void transfer(TransferDTO dto) {
        Account accountFrom = repository.get(new AccountNumber(dto.getAccountFrom()));
        Account accountTo = repository.get(new AccountNumber(dto.getAccountTo()));

        TransferMoney transfer = new TransferMoney();
        transfer.transfer(accountFrom, accountTo, dto.getValue());

        repository.add(accountFrom);
        repository.add(accountTo);
    }
}
