package com.devsu.account.application.dto;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class AccountResponseDTO {
    private Long id;
    private String accountNumber;
    private String type;
    private BigDecimal initialBalance;
    private Boolean state;
    private Long clientId;
    private String clientName;
    private List<MovementResponseDTO> movements;
}