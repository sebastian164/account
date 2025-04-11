package com.devsu.account.application.service;

import com.devsu.account.application.mapper.AccountMapper;
import com.devsu.account.domain.model.Account;
import com.devsu.account.domain.port.in.AccountServicePort;
import com.devsu.account.domain.port.out.AccountRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class AccountService implements AccountServicePort {

    private final AccountRepositoryPort repository;
    private final AccountMapper mapper;

    public AccountService(AccountRepositoryPort repository, AccountMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Account create(Account account) {
        return repository.save(account);
    }

    @Override
    public Account update(Long id, Account account) {
        Account existing = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada"));
        account.setId(existing.getId());
        return repository.save(account);
    }

    @Override
    public Account getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada"));
    }

    @Override
    public List<Account> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
