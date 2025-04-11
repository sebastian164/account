package com.devsu.account.infrastructure.persistence.mapper;

import com.devsu.account.domain.model.Movement;
import com.devsu.account.infrastructure.persistence.entity.AccountEntity;
import com.devsu.account.infrastructure.persistence.entity.MovementEntity;
import org.springframework.stereotype.Component;

@Component
public class MovementEntityMapper {

    public Movement toModel(MovementEntity entity) {
        if (entity == null) return null;
        Movement movement = new Movement();
        movement.setId(entity.getId());
        movement.setDate(entity.getDate());
        movement.setType(entity.getType());
        movement.setAmount(entity.getAmount());
        movement.setBalance(entity.getBalance());
        movement.setAccountId(entity.getAccount().getId());
        return movement;
    }

    public MovementEntity toEntity(Movement movement, AccountEntity accountEntity) {
        if (movement == null || accountEntity == null) return null;
        MovementEntity entity = new MovementEntity();
        entity.setId(movement.getId());
        entity.setDate(movement.getDate());
        entity.setType(movement.getType());
        entity.setAmount(movement.getAmount());
        entity.setBalance(movement.getBalance());
        entity.setAccount(accountEntity);
        return entity;
    }
}
