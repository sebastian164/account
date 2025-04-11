package com.devsu.account.domain.port.out;

import com.devsu.account.domain.model.Movement;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MovementRepositoryPort {
    Movement save(Movement movement);
    List<Movement> findByAccountId(Long accountId);
    List<Movement> findByAccountIdAndDateRange(Long accountId, LocalDate from, LocalDate to);

    Optional<Movement> findById(Long id);
}