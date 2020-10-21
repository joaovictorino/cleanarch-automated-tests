package com.bank.service.boot.bankboot;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import com.bank.account.application.TransferMoneyService;
import com.bank.account.application.dto.TransferDTO;
import com.bank.account.model.Account;
import com.bank.account.model.AccountNumber;
import com.bank.account.model.contract.Repository;

@RestController
public class TransferController {

    @Autowired
    Repository<Account, AccountNumber> accountRepository;

    @Transactional
    @RequestMapping("/")
    public String index() {
            Account accountFrom = new Account(new AccountNumber("123456"), 5000.0);
            Account accountTo = new Account(new AccountNumber("654321"), 5000.0);
            accountRepository.add(accountFrom);
            accountRepository.add(accountTo);
            return "teste";
    }

    @Transactional
    @RequestMapping("/transfer/{from}/{to}/{value}")
    public String index(@PathVariable String from, @PathVariable String to, @PathVariable String value) {
        try {
            TransferDTO dto = new TransferDTO();
            dto.setAccountFrom(from);
            dto.setAccountTo(to);
            dto.setValue(Double.parseDouble(value));
            TransferMoneyService service = new TransferMoneyService(accountRepository);
            return service.transfer(dto);
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }
}
