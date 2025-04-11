package com.devsu.account.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movement {
    private Long id;
    private LocalDateTime date;
    private String type;
    private BigDecimal amount;
    private BigDecimal balance;
    private Long accountId;
}
