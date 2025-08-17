package com.finance.finance_history.repositories;

import com.finance.finance_history.entities.Transaction;
import com.finance.finance_history.entities.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByUserId(Long userId);

    List<Transaction> findByUserIdAndTransactionDateBetween(Long userId, LocalDateTime startDate, LocalDateTime endDate);

    // Sum amounts grouped by TransactionType for a given user
    @Query("SELECT t.type, SUM(t.amount) FROM Transaction t WHERE t.user.id = :userId GROUP BY t.type")
    List<Object[]> getTotalByType(@Param("userId") Long userId);

    // Sum amounts grouped by category for a given user
    @Query("SELECT t.category, SUM(t.amount) FROM Transaction t WHERE t.user.id = :userId GROUP BY t.category")
    List<Object[]> getTotalByCategory(@Param("userId") Long userId);

    // Sum amount for a specific type and user (if you want to keep this)
    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.type = :type AND t.user.id = :userId")
    Double getTotalAmountByType(@Param("type") TransactionType type, @Param("userId") Long userId);
}
