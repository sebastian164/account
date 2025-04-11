package com.devsu.account.domain.port.in;

import com.devsu.account.domain.model.Movement;

import java.time.LocalDate;
import java.util.List;

public interface MovementServicePort {
    Movement create(Movement movement);
    List<Movement> getByAccountId(Long accountId);
    List<Movement> getByAccountIdAndDateRange(Long accountId, LocalDate from, LocalDate to);
}