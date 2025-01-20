package com.developer.library.controller;

import com.developer.library.model.Transaction;
import com.developer.library.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;


    @PostMapping("/borrow")
    public Transaction borrowBook(@RequestParam long userId, @RequestParam long bookId) {
        return transactionService.borrowBook(userId, bookId);
    }

    @PostMapping("/return")
    public Transaction returnBook(@RequestParam Long userId, @RequestParam Long bookId) {
        return transactionService.returnBook(userId, bookId);
    }

    @GetMapping("/user/{userId}")
    public List<Transaction> getUserTransactions(@PathVariable Long userId) {
        return transactionService.getTransactionsByUser(userId);
    }

    @GetMapping("/book/{bookId}")
    public List<Transaction> getBookTransactions(@PathVariable Long bookId) {
        return transactionService.getTransactionsByBook(bookId);
    }
}