package com.devsu.account.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private Long id;
    private String accountNumber;
    private String type;
    private BigDecimal initialBalance;
    private Boolean state;
    private Long clientId;
    private String clientName;
    private List<Movement> movements;
}
