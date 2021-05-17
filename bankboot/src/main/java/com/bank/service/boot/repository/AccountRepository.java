package com.bank.service.boot.repository;

import com.bank.account.model.contract.Repository;

import com.bank.account.model.Account;

import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.EntityManager;

@org.springframework.stereotype.Repository
public class AccountRepository implements Repository<Account, String> {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Account get(String accountNumber) {
        AccountMapping result = this.getInternal(accountNumber);

        return new Account(result.getNumber(), result.getBalance());
    }

    private AccountMapping getInternal(String field) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<AccountMapping> criteria = builder.createQuery(AccountMapping.class);
        Root<AccountMapping> from = criteria.from(AccountMapping.class);

        criteria.select(from).where(builder.equal(from.get("number"), field));

        TypedQuery<AccountMapping> typedAccount = entityManager.createQuery(criteria);

        return typedAccount.getSingleResult();
    }

    @Override
    public void add(Account account) {
        try {
            AccountMapping result = getInternal(account.getAccountNumber());
            result.setBalance(account.getBalance());
        } catch(Exception ex) {
            AccountMapping entity = new AccountMapping(account.getAccountNumber(), account.getBalance());
            entityManager.persist(entity);
        }
    }

}