package com.devsu.account.infrastructure.adapter;

import com.devsu.account.domain.port.out.AccountRepositoryPort;
import com.devsu.account.infrastructure.persistence.entity.AccountEntity;
import com.devsu.account.domain.model.Account;
import com.devsu.account.infrastructure.persistence.mapper.AccountEntityMapper;
import com.devsu.account.infrastructure.persistence.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AccountPersistenceAdapter implements AccountRepositoryPort {

    private final AccountRepository accountRepository;
    private final AccountEntityMapper accountEntityMapper;

    @Override
    public Account save(Account account) {
        AccountEntity saved = accountRepository.save(accountEntityMapper.toEntity(account));
        return accountEntityMapper.toDomain(saved);
    }

    @Override
    public Optional<Account> findById(Long id) {
        return accountRepository.findById(id).map(accountEntityMapper::toDomain);
    }

    @Override
    public List<Account> findAll() {
        return List.of();
    }

    @Override
    public void deleteById(Long id) {
        accountRepository.deleteById(id);
    }
}