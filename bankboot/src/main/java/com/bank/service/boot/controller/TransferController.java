package com.bank.service.boot.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.bank.account.application.TransferMoneyService;
import com.bank.account.application.dto.TransferDTO;
import com.bank.account.model.Account;
import com.bank.account.model.contract.Repository;

@RestController
public class TransferController {

    @Autowired
    Repository<Account, String> accountRepository;

    @Transactional
    @PostMapping("/transfer/{from}/{to}/{value}")
    public String index(@PathVariable String from, @PathVariable String to, @PathVariable String value) {
        try {
            TransferDTO dto = new TransferDTO();
            dto.setAccountFrom(from);
            dto.setAccountTo(to);
            dto.setValue(Double.parseDouble(value));
            TransferMoneyService service = new TransferMoneyService(accountRepository);
            return service.transfer(dto);
        } catch(IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
    }
}