package com.devsu.account.infrastructure.controller;

import com.devsu.account.application.dto.AccountRequestDTO;
import com.devsu.account.application.dto.AccountResponseDTO;
import com.devsu.account.application.mapper.AccountMapper;
import com.devsu.account.domain.port.in.AccountServicePort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/accounts")
class AccountController {

    private final AccountServicePort service;
    private final AccountMapper mapper;

    public AccountController(AccountServicePort service, AccountMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public AccountResponseDTO create(@RequestBody AccountRequestDTO dto) {
        return mapper.toDto(service.create(mapper.toModel(dto)));
    }

    @GetMapping("/{id}")
    public AccountResponseDTO getById(@PathVariable Long id) {
        return mapper.toDto(service.getById(id));
    }

    @GetMapping
    public List<AccountResponseDTO> getAll() {
        return service.getAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public AccountResponseDTO update(@PathVariable Long id, @RequestBody AccountRequestDTO dto) {
        return mapper.toDto(service.update(id, mapper.toModel(dto)));
    }

}