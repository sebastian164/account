package com.devsu.account.application.mapper;

import com.devsu.account.application.dto.AccountRequestDTO;
import com.devsu.account.application.dto.AccountResponseDTO;
import com.devsu.account.domain.model.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public Account toModel(AccountRequestDTO dto) {
        Account account = new Account();
        account.setAccountNumber(dto.getAccountNumber());
        account.setType(dto.getType());
        account.setInitialBalance(dto.getInitialBalance());
        account.setState(dto.getState());
        account.setClientId(dto.getClientId());
        account.setClientName(dto.getClientName());
        return account;
    }

    public AccountResponseDTO toDto(Account model) {
        AccountResponseDTO dto = new AccountResponseDTO();
        dto.setId(model.getId());
        dto.setAccountNumber(model.getAccountNumber());
        dto.setType(model.getType());
        dto.setInitialBalance(model.getInitialBalance());
        dto.setState(model.getState());
        dto.setClientId(model.getClientId());
        dto.setClientName(model.getClientName());
        return dto;
    }
}