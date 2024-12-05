package com.example.pfm.controller;

import com.example.pfm.model.Transaction;
import com.example.pfm.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    // 1. Endpoint to filter by category and amount threshold
    @GetMapping("/filterByCategoryAndAmount")
    public ResponseEntity<List<Transaction>> getFilteredTransactions(
            @RequestParam String category, @RequestParam Double amount) {
        List<Transaction> transactions = transactionService.getTransactionsByCategoryAndAmount(category, amount);
        return ResponseEntity.ok(transactions);
    }

    // 2. Endpoint to filter by date range
    @GetMapping("/filterByDateRange")
    public ResponseEntity<List<Transaction>> getTransactionsByDateRange(
            @RequestParam String startDate, @RequestParam String endDate) {
        try {
            Date start = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
            Date end = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
            List<Transaction> transactions = transactionService.getTransactionsByDateRange(start, end);
            return ResponseEntity.ok(transactions);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // 3. Endpoint to filter by user ID and category
    @GetMapping("/filterByUserAndCategory")
    public ResponseEntity<List<Transaction>> getTransactionsByUserIdAndCategory(
            @RequestParam Long userId, @RequestParam String category) {
        List<Transaction> transactions = transactionService.getTransactionsByUserIdAndCategory(userId, category);
        return ResponseEntity.ok(transactions);
    }
}
