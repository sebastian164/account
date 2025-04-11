package com.devsu.account.application.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class AccountRequestDTO {
    private String accountNumber;
    private String type;
    private BigDecimal initialBalance;
    private Boolean state;
    private Long clientId;
    private String clientName;
}