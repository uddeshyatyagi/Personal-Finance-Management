package com.example.pfm.repository;

import com.example.pfm.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // 1. Find transactions by category and amount greater than threshold
    List<Transaction> findByCategoryAndAmountGreaterThan(String category, Double threshold);

    // 2. Find transactions by date range
    List<Transaction> findByDateBetween(Date startDate, Date endDate);

    // 3. Find transactions by user ID and category
    List<Transaction> findByUser_IdAndCategory(Long userId, String category);
}
