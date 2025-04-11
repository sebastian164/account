package com.devsu.account.application.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class MovementResponseDTO {
    private Long id;
    private LocalDateTime date;
    private String type;
    private BigDecimal amount;
    private BigDecimal balance;
    private Long accountId;
}