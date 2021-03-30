package com.bank.service.boot.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.bank.account.model.Account;
import com.bank.account.model.AccountNumber;
import com.bank.account.model.contract.Repository;

@RestController
public class AccountController {

    @Autowired
    Repository<Account, AccountNumber> accountRepository;

    @Transactional
    @RequestMapping("/account/{number}")
    public Account index(@PathVariable String number) {
        return accountRepository.get(new AccountNumber(number));
    }

    @Transactional
    @PostMapping("/account/{number}/{balance}")
    @ResponseStatus(HttpStatus.OK)
    public String createAccount(@PathVariable String number, @PathVariable double balance) {
        try {
            accountRepository.add(new Account(new AccountNumber(number), balance));
            return "account created";
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
    }
}
