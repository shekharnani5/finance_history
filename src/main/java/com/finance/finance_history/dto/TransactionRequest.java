package com.finance.finance_history.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class TransactionRequest {

    private String type; // "INCOME" or "EXPENSE"
    private String category;
    private Double amount;
    private LocalDate transactionDate; // optional
    private String description;

    // getters and setters
}