package com.devsu.account.infrastructure.adapter;

import com.devsu.account.domain.model.Movement;
import com.devsu.account.domain.port.out.MovementRepositoryPort;
import com.devsu.account.infrastructure.persistence.entity.AccountEntity;
import com.devsu.account.infrastructure.persistence.entity.MovementEntity;
import com.devsu.account.infrastructure.persistence.mapper.MovementEntityMapper;
import com.devsu.account.infrastructure.persistence.repository.AccountRepository;
import com.devsu.account.infrastructure.persistence.repository.MovementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MovementPersistenceAdapter implements MovementRepositoryPort {

    private final MovementRepository movementRepository;
    private final AccountRepository accountRepository;
    private final MovementEntityMapper mapper;

    @Override
    public Movement save(Movement movement) {
        AccountEntity accountEntity = accountRepository.findById(movement.getAccountId())
                .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada con ID: " + movement.getAccountId()));

        MovementEntity entity = mapper.toEntity(movement, accountEntity);
        MovementEntity saved = movementRepository.save(entity);

        return mapper.toModel(saved);
    }

    @Override
    public List<Movement> findByAccountId(Long accountId) {
        return movementRepository.findByAccountId(accountId)
                .stream()
                .map(mapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Movement> findByAccountIdAndDateRange(Long accountId, LocalDate from, LocalDate to) {
        LocalDateTime fromDateTime = from.atStartOfDay();
        LocalDateTime toDateTime = to.atTime(LocalTime.MAX);

        return movementRepository.findByAccountIdAndDateRange(accountId, fromDateTime, toDateTime)
                .stream()
                .map(mapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Movement> findById(Long id) {
        return movementRepository.findById(id)
                .map(mapper::toModel);
    }
}
