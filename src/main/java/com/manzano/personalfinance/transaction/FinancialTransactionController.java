package com.manzano.personalfinance.transaction;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/transactions")
public class FinancialTransactionController {

    private final FinancialTransactionService service;

    public FinancialTransactionController(FinancialTransactionService service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<FinancialTransaction> createTransaction(@Valid @RequestBody CreateFinancialTransactionRequest request){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(request));
    }
}
