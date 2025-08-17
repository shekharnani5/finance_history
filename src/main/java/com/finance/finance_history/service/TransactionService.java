package com.finance.finance_history.service;

import com.finance.finance_history.dto.AnalyticsResponse;
import com.finance.finance_history.dto.TransactionRequest;
import com.finance.finance_history.entities.Transaction;
import com.finance.finance_history.entities.TransactionType;
import com.finance.finance_history.entities.User;
import com.finance.finance_history.repositories.TransactionRepository;
import com.finance.finance_history.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public TransactionService(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    public Transaction addTransaction(TransactionRequest request) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setType(TransactionType.valueOf(request.getType()));
        transaction.setCategory(request.getCategory());
        transaction.setAmount(BigDecimal.valueOf(request.getAmount()));

        // Fix: Use LocalDateTime.now() if transactionDate is null
        LocalDateTime transactionDate = request.getTransactionDate() != null
                ? LocalDateTime.from(request.getTransactionDate())
                : LocalDateTime.now();
        transaction.setTransactionDate(transactionDate);

        transaction.setDescription(request.getDescription());

        return transactionRepository.save(transaction);
    }

    public List<Transaction> getUserTransactions() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return transactionRepository.findByUserId(user.getId());
    }

    public AnalyticsResponse getAnalyticsForUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Object[]> byType = transactionRepository.getTotalByType(user.getId());
        List<Object[]> byCategory = transactionRepository.getTotalByCategory(user.getId());

        Map<String, Double> totalByType = new HashMap<>();
        for (Object[] row : byType) {
            totalByType.put(row[0].toString(), ((Number) row[1]).doubleValue());
        }

        Map<String, Double> totalByCategory = new HashMap<>();
        for (Object[] row : byCategory) {
            totalByCategory.put(row[0].toString(), ((Number) row[1]).doubleValue());
        }

        AnalyticsResponse response = new AnalyticsResponse();
        response.setTotalByType(totalByType);
        response.setTotalByCategory(totalByCategory);

        return response;
    }
}
