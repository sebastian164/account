package com.devsu.account.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "account")
@Data
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String accountNumber;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private BigDecimal initialBalance;

    @Column(nullable = false)
    private Boolean state;

    @Column(nullable = false)
    private Long clientId;

    @Column(nullable = false)
    private String clientName;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<MovementEntity> movements;
}