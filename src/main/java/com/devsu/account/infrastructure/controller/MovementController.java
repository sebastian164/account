package com.devsu.account.infrastructure.controller;

import com.devsu.account.application.dto.MovementRequestDTO;
import com.devsu.account.application.dto.MovementResponseDTO;
import com.devsu.account.application.mapper.MovementMapper;
import com.devsu.account.domain.port.in.MovementServicePort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/movements")
class MovementController {

    private final MovementServicePort service;
    private final MovementMapper mapper;

    public MovementController(MovementServicePort service, MovementMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public MovementResponseDTO create(@RequestBody MovementRequestDTO dto) {
        return mapper.toDto(service.create(mapper.toModel(dto)));
    }

    @GetMapping("/account/{accountId}")
    public List<MovementResponseDTO> getByAccount(@PathVariable Long accountId) {
        return service.getByAccountId(accountId).stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("/account/{accountId}/range")
    public List<MovementResponseDTO> getByAccountAndDateRange(
            @PathVariable Long accountId,
            @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to
    ) {
        return service.getByAccountIdAndDateRange(accountId, from, to).stream().map(mapper::toDto).collect(Collectors.toList());
    }
}
