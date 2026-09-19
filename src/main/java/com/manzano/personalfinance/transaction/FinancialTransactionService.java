package com.manzano.personalfinance.transaction;

import com.manzano.personalfinance.exception.TransactionNotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    public List<FinancialTransactionResponse> getTransactions(){
        return repository.findAllByOrderByDateDesc()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public FinancialTransactionResponse getTransactionById(Long id){

        FinancialTransaction transaction = repository.findById(id)
                .orElseThrow(()->
                                new TransactionNotFoundException(
                                        "Transaction not found with id: " + id
                                )
                        );

        return toResponse(transaction);
    }

    private FinancialTransactionResponse toResponse(FinancialTransaction transaction){

        return new FinancialTransactionResponse(transaction.getId(),
                transaction.getDescription(),
                transaction.getAmount(),
                transaction.getType(),
                transaction.getDate());
    }

}
