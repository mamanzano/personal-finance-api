package com.manzano.personalfinance.transaction;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FinancialTransactionServiceTest {

    @Mock
    private FinancialTransactionRepository repository;
    @InjectMocks
    private FinancialTransactionService service;


    @Test
    void shouldCreateFinancialTransaction(){

        //Arrange
        CreateFinancialTransactionRequest request = new CreateFinancialTransactionRequest();

        request.setDescription("Supermercado");
        request.setAmount(new BigDecimal("850.50"));
        request.setType(TransactionType.EXPENSE);
        request.setDate(LocalDate.now());

        FinancialTransaction savedTransaction =
                new FinancialTransaction();

        savedTransaction.setDescription("Supermercado");
        savedTransaction.setAmount(new BigDecimal("850.50"));
        savedTransaction.setType(TransactionType.EXPENSE);
        savedTransaction.setDate(LocalDate.now());

        when(repository.save(any(FinancialTransaction.class)))
                .thenReturn(savedTransaction);

        //ACT
        FinancialTransaction result = service.create(request);

        //Asserts
        assertNotNull(result);
        assertEquals("Supermercado", result.getDescription());
        assertEquals(new BigDecimal("850.50"), result.getAmount());
        assertEquals(TransactionType.EXPENSE, result.getType());

        verify(repository).save(any(FinancialTransaction.class));
    }

}
