package com.devsu.account;

import com.devsu.account.application.service.MovementService;
import com.devsu.account.domain.model.Account;
import com.devsu.account.domain.model.Movement;
import com.devsu.account.domain.port.out.AccountRepositoryPort;
import com.devsu.account.domain.port.out.MovementRepositoryPort;
import com.devsu.account.infrastructure.handler.BusinessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MovementServiceTest {

    private MovementRepositoryPort movementRepository;
    private AccountRepositoryPort accountRepository;
    private MovementService service;

    @BeforeEach
    void setUp() {
        movementRepository = mock(MovementRepositoryPort.class);
        accountRepository = mock(AccountRepositoryPort.class);
        service = new MovementService(movementRepository, accountRepository);
    }

    @Test
    void testCreateDeposit_success() {
        Account account = Account.builder()
                .id(1L)
                .accountNumber("123")
                .type("AHORROS")
                .initialBalance(BigDecimal.valueOf(1000))
                .state(true)
                .clientId(1L)
                .build();

        Movement movement = Movement.builder()
                .accountId(1L)
                .type("DEPOSITO")
                .amount(BigDecimal.valueOf(500))
                .date(LocalDateTime.now())
                .build();

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        when(movementRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        Movement result = service.create(movement);

        assertNotNull(result);
        assertEquals(BigDecimal.valueOf(1500), result.getBalance());
        assertEquals(BigDecimal.valueOf(500), result.getAmount());
        verify(accountRepository).save(account);
    }

    @Test
    void testCreateRemoveDeposit_success() {
        Account account = Account.builder()
                .id(1L)
                .accountNumber("123")
                .type("AHORROS")
                .initialBalance(BigDecimal.valueOf(1000))
                .state(true)
                .clientId(1L)
                .build();

        Movement movement = Movement.builder()
                .accountId(1L)
                .type("RETIRO")
                .amount(BigDecimal.valueOf(500))
                .date(LocalDateTime.now())
                .build();

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        when(movementRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        Movement result = service.create(movement);

        assertNotNull(result);
        assertEquals(BigDecimal.valueOf(500), result.getBalance());
        assertEquals(BigDecimal.valueOf(-500), result.getAmount());
        verify(accountRepository).save(account);
    }

    @Test
    void testCreateWithdrawal_success() {
        Account account = Account.builder()
                .id(1L)
                .accountNumber("123")
                .type("AHORROS")
                .initialBalance(BigDecimal.valueOf(1000))
                .state(true)
                .clientId(1L)
                .build();

        Movement movement = Movement.builder()
                .accountId(1L)
                .type("RETIRO")
                .amount(BigDecimal.valueOf(200))
                .date(LocalDateTime.now())
                .build();

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        when(movementRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        Movement result = service.create(movement);

        assertNotNull(result);
        assertEquals(BigDecimal.valueOf(800), result.getBalance());
        assertEquals(BigDecimal.valueOf(-200), result.getAmount());
        verify(accountRepository).save(account);
    }

    @Test
    void testCreateWithdrawal_insufficientBalance() {
        Account account = Account.builder()
                .id(1L)
                .accountNumber("123")
                .type("AHORROS")
                .initialBalance(BigDecimal.valueOf(100))
                .state(true)
                .clientId(1L)
                .build();

        Movement movement = Movement.builder()
                .accountId(1L)
                .type("RETIRO")
                .amount(BigDecimal.valueOf(200))
                .date(LocalDateTime.now())
                .build();

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        BusinessException exception = assertThrows(BusinessException.class, () -> service.create(movement));
        assertEquals("Saldo insuficiente para realizar el retiro", exception.getMessage());
        verify(movementRepository, never()).save(any());
        verify(accountRepository, never()).save(any());
    }

    @Test
    void testCreate_accountNotFound() {
        when(accountRepository.findById(1L)).thenReturn(Optional.empty());

        Movement movement = Movement.builder()
                .accountId(1L)
                .type("DEPOSITO")
                .amount(BigDecimal.valueOf(100))
                .date(LocalDateTime.now())
                .build();

        BusinessException exception = assertThrows(BusinessException.class, () -> service.create(movement));
        assertEquals("Cuenta no encontrada", exception.getMessage());
    }
}
