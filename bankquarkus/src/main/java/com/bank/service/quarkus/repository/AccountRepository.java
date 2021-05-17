package com.bank.service.quarkus.repository;

import com.bank.account.model.contract.Repository;
import com.bank.account.model.Account;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.ReplaceOptions;
import static com.mongodb.client.model.Filters.eq;

@ApplicationScoped
public class AccountRepository implements Repository<Account, String> {

    @Inject
    MongoClient mongoClient;

    @Override
    public Account get(String accountNumber) {
        AccountMapping mapping = getInternal(accountNumber);
        return new Account(mapping.getNumber(), mapping.getBalance());
    }

    private AccountMapping getInternal(String accountNumber) {
        return getCollection().find(eq("number", accountNumber)).first();
    }

    @Override
    public void add(Account account) {
        AccountMapping mapping = getInternal(account.getAccountNumber());

        if(mapping == null) {
            AccountMapping mappingNew = new AccountMapping();
            mappingNew.setBalance(account.getBalance());
            mappingNew.setNumber(account.getAccountNumber());
            getCollection().insertOne(mappingNew);
        } else { 
            mapping.setBalance(account.getBalance());
            getCollection().replaceOne(eq("number", account.getAccountNumber()), mapping, new ReplaceOptions().upsert(true).bypassDocumentValidation(true));
        }
    }

    private MongoCollection<AccountMapping> getCollection() {
        return mongoClient.getDatabase("account").getCollection("account", AccountMapping.class);
    }

}