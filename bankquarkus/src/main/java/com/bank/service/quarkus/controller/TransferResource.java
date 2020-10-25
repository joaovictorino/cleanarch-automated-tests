package com.bank.service.quarkus.controller;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import javax.enterprise.context.RequestScoped;

import com.bank.account.application.TransferMoneyService;
import com.bank.account.application.dto.TransferDTO;
import com.bank.account.model.Account;
import com.bank.account.model.AccountNumber;
import com.bank.account.model.contract.Repository;

@RequestScoped
@Path("/transfer")
public class TransferResource {

    @Inject
    Repository<Account, AccountNumber> accountRepository;

    @GET
    public String index() {
        Account accountFrom = new Account(new AccountNumber("123456"), 5000.0);
        Account accountTo = new Account(new AccountNumber("654321"), 5000.0);
        accountRepository.add(accountFrom);
        accountRepository.add(accountTo);
        return "OK";
    }

    @GET
    @Path("{from}/{to}/{value}")
    @Produces(MediaType.APPLICATION_JSON)
    public String transfer(@PathParam("from") String from, @PathParam("to") String to, @PathParam("value") String value)  {
        try {
            TransferDTO dto = new TransferDTO();
            dto.setAccountFrom(from);
            dto.setAccountTo(to);
            dto.setValue(Double.parseDouble(value));
            TransferMoneyService service = new TransferMoneyService(accountRepository);
            return service.transfer(dto);
        } catch (Exception ex) {
            if (ex.getMessage() != null) {
                return ex.getMessage();
            } else {
                return "erro " + ex.toString();
            }
        }
    }
}