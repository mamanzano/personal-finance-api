package com.manzano.personalfinance.transaction;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FinancialTransactionService {

    private final FinancialTransactionRepository repository;

    public FinancialTransactionService(FinancialTransactionRepository repository) {
        this.repository = repository;
    }

    public FinancialTransaction create(CreateFinancialTransactionRequest request){
        FinancialTransaction transaction = new FinancialTransaction();

        transaction.setAmount(request.getAmount());
        transaction.setDate(request.getDate());
        transaction.setDescription(request.getDescription());
        transaction.setType(request.getType());

        return repository.save(transaction);
    }

}
