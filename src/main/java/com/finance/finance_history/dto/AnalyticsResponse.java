package com.finance.finance_history.dto;

import lombok.Data;

import java.util.Map;
@Data
public class AnalyticsResponse {
    private Map<String, Double> totalByType;      // e.g., {"INCOME": 5000.0, "EXPENSE": 3000.0}
    private Map<String, Double> totalByCategory;  // e.g., {"Salary": 5000.0, "Food": 1000.0, "Rent": 2000.0}
}
