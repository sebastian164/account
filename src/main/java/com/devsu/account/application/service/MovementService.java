package com.devsu.account.application.service;

import com.devsu.account.domain.model.Account;
import com.devsu.account.domain.model.Movement;
import com.devsu.account.domain.port.in.MovementServicePort;
import com.devsu.account.domain.port.out.MovementRepositoryPort;
import com.devsu.account.domain.port.out.AccountRepositoryPort;
import com.devsu.account.infrastructure.handler.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class MovementService implements MovementServicePort {

    private final MovementRepositoryPort movementRepository;
    private final AccountRepositoryPort accountRepository;

    public MovementService(MovementRepositoryPort movementRepository, AccountRepositoryPort accountRepository) {
        this.movementRepository = movementRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public Movement create(Movement movement) {
        Account account = accountRepository.findById(movement.getAccountId())
                .orElseThrow(() -> new BusinessException("Cuenta no encontrada"));

        BigDecimal currentBalance = account.getInitialBalance();
        BigDecimal amount = movement.getAmount();

        if ("RETIRO".equalsIgnoreCase(movement.getType())) {
            amount = amount.negate();
        }

        BigDecimal newBalance = currentBalance.add(amount);

        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new BusinessException("Saldo insuficiente para realizar el retiro");
        }

        movement.setAmount(amount);
        movement.setDate(LocalDateTime.now());
        movement.setBalance(newBalance);

        Movement savedMovement = movementRepository.save(movement);
        account.setInitialBalance(newBalance);
        accountRepository.save(account);

        return savedMovement;
    }

    @Override
    public List<Movement> getByAccountId(Long accountId) {
        return movementRepository.findByAccountId(accountId);
    }

    @Override
    public List<Movement> getByAccountIdAndDateRange(Long accountId, LocalDate from, LocalDate to) {
        return movementRepository.findByAccountIdAndDateRange(accountId, from, to);
    }
}
