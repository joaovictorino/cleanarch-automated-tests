package com.bank.service.quarkus.controller;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.transaction.SystemException;
import javax.transaction.TransactionManager;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.enterprise.context.RequestScoped;

import com.bank.account.application.TransferMoneyService;
import com.bank.account.application.dto.TransferDTO;
import com.bank.account.model.Account;
import com.bank.account.model.contract.Repository;

@RequestScoped
@Path("/transfer")
public class TransferResource {

    @Inject
    Repository<Account, String> accountRepository;

    @Inject 
    TransactionManager tm;

    @GET
    public String index() {
        Account accountFrom = new Account("123456", 5000.0);
        Account accountTo = new Account("654321", 5000.0);
        accountRepository.add(accountFrom);
        accountRepository.add(accountTo);
        return "OK";
    }

    @POST
    @Path("{from}/{to}/{value}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response transfer(@PathParam("from") String from, @PathParam("to") String to, @PathParam("value") String value) throws IllegalStateException, SystemException  {
        try {
            TransferDTO dto = new TransferDTO();
            dto.setAccountFrom(from);
            dto.setAccountTo(to);
            dto.setValue(Double.parseDouble(value));
            TransferMoneyService service = new TransferMoneyService(accountRepository);
            return Response.ok(service.transfer(dto)).build();
        } catch(IllegalArgumentException ex) {
            tm.setRollbackOnly();
            return Response.status(Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }
}