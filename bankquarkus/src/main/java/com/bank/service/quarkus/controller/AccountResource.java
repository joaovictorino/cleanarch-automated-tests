package com.bank.service.quarkus.controller;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import javax.enterprise.context.RequestScoped;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import com.bank.account.model.Account;
import com.bank.account.model.AccountNumber;
import com.bank.account.model.contract.Repository;

@RequestScoped
@Path("/account")
public class AccountResource {

    @Inject
    Repository<Account, AccountNumber> accountRepository;

    @GET
    @Path("{number}")
    @Produces(MediaType.APPLICATION_JSON)
    public String index(@PathParam("number") String number) {
        return String.valueOf(accountRepository.get(new AccountNumber(number)).getBalance());
    }
    
}
