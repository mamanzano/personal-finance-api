package com.manzano.personalfinance.transaction;

/*

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

 */
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
public class FinancialTransactionController {

    private final FinancialTransactionService service;

    public FinancialTransactionController(FinancialTransactionService service) {
        this.service = service;
    }


    /*
    @Operation(
            summary = "Crear una transacción financiera",
            description = "Registra una nueva transacción de tipo ingreso o gasto"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Transacción creada correctamente"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos de entrada inválidos"
            )
    })

     */
    @PostMapping()
    public ResponseEntity<FinancialTransaction> createTransaction(@Valid @RequestBody CreateFinancialTransactionRequest request){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(request));
    }


    @GetMapping
    public ResponseEntity<List<FinancialTransactionResponse>> getTransactions(){
        return ResponseEntity.ok(service.getTransactions());
    }
}
