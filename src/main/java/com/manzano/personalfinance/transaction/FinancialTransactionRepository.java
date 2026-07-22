package com.manzano.personalfinance.transaction;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FinancialTransactionRepository extends JpaRepository<FinancialTransaction,Long> {
}
