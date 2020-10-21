package com.bank.service.boot.bankboot;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
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
}
