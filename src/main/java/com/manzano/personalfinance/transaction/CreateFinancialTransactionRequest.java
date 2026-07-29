package com.manzano.personalfinance.transaction;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;


public class CreateFinancialTransactionRequest {

    @NotBlank(message = "Incluir una breve descripción")
    private String description;
    @NotNull(message = "Incluir el monto de la transacción")
    @Positive(message = "El monto debe ser mayor a 0")
    private BigDecimal amount;
    @NotNull(message = "Incluir el tipo de transacción")
    private TransactionType type;
    @NotNull(message = "Incluir la fecha en que se realizó la transacción")
    @PastOrPresent(message = "La fecha de la transacción no puede ser futura")
    private LocalDate date;



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
