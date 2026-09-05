package com.manzano.personalfinance.transaction;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FinancialTransactionRepository extends JpaRepository<FinancialTransaction,Long> {
    List<FinancialTransaction> findAllByOrderByDateDesc();
}
