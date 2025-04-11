package com.devsu.account.infrastructure.persistence.repository;

import com.devsu.account.infrastructure.persistence.entity.MovementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface MovementRepository extends JpaRepository<MovementEntity, Long> {

    List<MovementEntity> findByAccountId(Long accountId);

    @Query("SELECT m FROM MovementEntity m WHERE m.account.id = :accountId AND m.date BETWEEN :from AND :to")
    List<MovementEntity> findByAccountIdAndDateRange(
            @Param("accountId") Long accountId,
            @Param("from") LocalDateTime from,
            @Param("to") LocalDateTime to
    );
}
