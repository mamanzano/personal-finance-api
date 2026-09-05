package com.manzano.personalfinance.transaction;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
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

    @Test
    void shouldGetTransactionsOrderedByDateDesc(){
        //Arrenge

        LocalDate transactionDate = LocalDate.of(2026, 9, 5);
        List<FinancialTransaction> transactionList = new ArrayList<>();

        FinancialTransaction transaction = new FinancialTransaction();
        transaction.setDescription("Supermercado");
        transaction.setType(TransactionType.EXPENSE);
        transaction.setAmount(new BigDecimal("100.50"));
        transaction.setDate(transactionDate);

        transactionList.add(transaction);

        when(repository.findAllByOrderByDateDesc())
                .thenReturn(transactionList);

        //Act
        List<FinancialTransactionResponse> transactions = service.getTransactions();


        //Asserts
        assertNotNull(transactions);
        assertEquals(1,transactions.size());

        FinancialTransactionResponse response = transactions.get(0);

        assertEquals("Supermercado", response.getDescription());
        assertEquals(new BigDecimal("100.50"), response.getAmount());
        assertEquals(TransactionType.EXPENSE, response.getType());
        assertEquals(transactionDate, response.getDate());

        verify(repository).findAllByOrderByDateDesc();

    }

    @Test
    void shouldReturnEmptyListWhenNoTransactionsExist() {




        when(repository.findAllByOrderByDateDesc())
                .thenReturn(List.of());

        List<FinancialTransactionResponse> transactions = service.getTransactions();

        assertNotNull(transactions);
        assertTrue(transactions.isEmpty());

        verify(repository).findAllByOrderByDateDesc();
    }

}
