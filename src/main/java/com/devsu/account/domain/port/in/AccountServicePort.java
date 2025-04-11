package com.devsu.account.domain.port.in;

import com.devsu.account.domain.model.Account;

import java.util.List;

public interface AccountServicePort {
    Account create(Account account);
    Account update(Long id, Account account);
    Account getById(Long id);
    List<Account> getAll();
    void delete(Long id);
}