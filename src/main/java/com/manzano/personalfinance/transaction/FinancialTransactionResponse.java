package com.manzano.personalfinance.transaction;

import java.math.BigDecimal;
import java.time.LocalDate;

public class FinancialTransactionResponse {

    private final Long id;
    private final String description;
    private final BigDecimal amount;
    private final TransactionType type;
    private final LocalDate date;

    public FinancialTransactionResponse(Long id, String description, BigDecimal amount, TransactionType type, LocalDate date) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.type = type;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public TransactionType getType() {
        return type;
    }

    public LocalDate getDate() {
        return date;
    }
}
