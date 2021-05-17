package com.bank.service.quarkus.controller;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.enterprise.context.RequestScoped;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import com.bank.account.model.Account;
import com.bank.account.model.contract.Repository;

@RequestScoped
@Path("/account")
public class AccountResource {

    @Inject
    Repository<Account, String> accountRepository;

    @GET
    @Path("{number}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response index(@PathParam("number") String number) {
        return Response.ok(accountRepository.get(number)).build();
    }

    @POST
    @Path("{number}/{balance}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAccount(@PathParam("number") String number, @PathParam("balance") double balance) {
        try {
            accountRepository.add(new Account(number, balance));
            return Response.ok("account created").build();
        } catch (IllegalArgumentException ex) {
            return Response.status(Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }
    
}
