package com.devsu.account.application.mapper;

import com.devsu.account.application.dto.MovementRequestDTO;
import com.devsu.account.application.dto.MovementResponseDTO;
import com.devsu.account.domain.model.Movement;
import org.springframework.stereotype.Component;

@Component
public class MovementMapper {

    public Movement toModel(MovementRequestDTO dto) {
        if (dto == null) return null;
        Movement movement = new Movement();
        movement.setAccountId(dto.getAccountId());
        movement.setType(dto.getType());
        movement.setAmount(dto.getAmount());
        return movement;
    }

    public MovementResponseDTO toDto(Movement movement) {
        if (movement == null) return null;
        MovementResponseDTO dto = new MovementResponseDTO();
        dto.setId(movement.getId());
        dto.setDate(movement.getDate()); // ya está como LocalDateTime
        dto.setType(movement.getType());
        dto.setAmount(movement.getAmount());
        dto.setBalance(movement.getBalance());
        dto.setAccountId(movement.getAccountId());
        return dto;
    }
}
