package com.finance.finance_history.controller;


import com.finance.finance_history.dto.AnalyticsResponse;
import com.finance.finance_history.dto.TransactionRequest;
import com.finance.finance_history.entities.Transaction;
import com.finance.finance_history.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<Transaction> addTransaction(@RequestBody TransactionRequest request) {
        Transaction savedTransaction = transactionService.addTransaction(request);
        return ResponseEntity.ok(savedTransaction);
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getTransactions() {
        List<Transaction> transactions = transactionService.getUserTransactions();
        return ResponseEntity.ok(transactions);
    }
    @GetMapping("/analytics")
    public ResponseEntity<AnalyticsResponse> getAnalytics() {
        AnalyticsResponse analytics = transactionService.getAnalyticsForUser();
        return ResponseEntity.ok(analytics);
    }

}
