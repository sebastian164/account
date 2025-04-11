package com.devsu.account.application.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MovementRequestDTO {
    private Long accountId;
    private String type;
    private BigDecimal amount;
}