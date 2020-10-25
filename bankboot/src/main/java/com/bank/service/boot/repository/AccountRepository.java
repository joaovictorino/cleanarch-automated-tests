package com.bank.service.boot.repository;

import com.bank.account.model.contract.Repository;

import com.bank.account.model.Account;
import com.bank.account.model.AccountNumber;

import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.EntityManager;

@org.springframework.stereotype.Repository
public class AccountRepository implements Repository<Account, AccountNumber> {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Account get(AccountNumber accountNumber) {
        AccountMapping result = get(accountNumber.getNumber());

        return new Account(new AccountNumber(result.getNumber()), result.getBalance());
    }

    private AccountMapping get(String field) {
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
            AccountMapping result = get(account.getAccountNumber().getNumber());
            result.setBalance(account.getBalance());
        } catch(Exception ex) {
            AccountMapping entity = new AccountMapping(account.getAccountNumber().getNumber(), account.getBalance());
            entityManager.persist(entity);
        }
    }

}