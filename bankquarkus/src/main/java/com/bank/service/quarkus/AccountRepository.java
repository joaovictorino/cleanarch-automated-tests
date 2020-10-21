package com.bank.service.quarkus;

import com.bank.account.model.contract.Repository;
import com.bank.account.model.Account;
import com.bank.account.model.AccountNumber;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.ReplaceOptions;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.FindIterable;
import static com.mongodb.client.model.Filters.eq;
import org.bson.Document;

@ApplicationScoped
public class AccountRepository implements Repository<Account, AccountNumber> {

    @Inject
    MongoClient mongoClient;

    @Override
    public Account get(AccountNumber accountNumber) {
        AccountMapping mapping = get(accountNumber.getNumber());
        return new Account(new AccountNumber(mapping.getNumber()), mapping.getBalance());
    }

    private AccountMapping get(String accountNumber) {
        return getCollection().find(eq("number", accountNumber)).first();
    }

    @Override
    public void add(Account account) {
        AccountMapping mapping = get(account.getAccountNumber().getNumber());

        if(mapping == null) {
            AccountMapping mappingNew = new AccountMapping();
            mappingNew.setBalance(account.getBalance());
            mappingNew.setNumber(account.getAccountNumber().getNumber());
            getCollection().insertOne(mappingNew);
        } else { 
            mapping.setBalance(account.getBalance());
            getCollection().replaceOne(eq("number", account.getAccountNumber().getNumber()), mapping, new ReplaceOptions().upsert(true).bypassDocumentValidation(true));
        }
    }

    private MongoCollection<AccountMapping> getCollection() {
        return mongoClient.getDatabase("account").getCollection("account", AccountMapping.class);
    }

}