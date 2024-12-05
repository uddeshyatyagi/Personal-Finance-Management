package com.example.pfm.service;

import com.example.pfm.model.Transaction;
import com.example.pfm.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    // 1. Filter by Category and Amount Threshold
    public List<Transaction> getTransactionsByCategoryAndAmount(String category, Double threshold) {
        return transactionRepository.findByCategoryAndAmountGreaterThan(category, threshold);
    }

    // 2. Filter by Date Range
    public List<Transaction> getTransactionsByDateRange(Date startDate, Date endDate) {
        return transactionRepository.findByDateBetween(startDate, endDate);
    }

    // 3. Filter by User ID and Category
    public List<Transaction> getTransactionsByUserIdAndCategory(Long userId, String category) {
        return transactionRepository.findByUser_IdAndCategory(userId, category);
    }
}
