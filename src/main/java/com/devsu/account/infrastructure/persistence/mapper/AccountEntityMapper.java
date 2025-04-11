package com.devsu.account.infrastructure.persistence.mapper;

import com.devsu.account.domain.model.Account;
import com.devsu.account.infrastructure.persistence.entity.AccountEntity;
import org.springframework.stereotype.Component;

@Component
public class AccountEntityMapper {

    public AccountEntity toEntity(Account account) {
        AccountEntity entity = new AccountEntity();
        entity.setId(account.getId());
        entity.setAccountNumber(account.getAccountNumber());
        entity.setType(account.getType());
        entity.setInitialBalance(account.getInitialBalance());
        entity.setState(account.getState());
        entity.setClientId(account.getClientId());
        entity.setClientName(account.getClientName());
        return entity;
    }

    public Account toDomain(AccountEntity entity) {
        Account account = new Account();
        account.setId(entity.getId());
        account.setAccountNumber(entity.getAccountNumber());
        account.setType(entity.getType());
        account.setInitialBalance(entity.getInitialBalance());
        account.setState(entity.getState());
        account.setClientId(entity.getClientId());
        account.setClientName(entity.getClientName());
        return account;
    }
}
