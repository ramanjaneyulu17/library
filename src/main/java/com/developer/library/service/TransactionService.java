package com.developer.library.service;

import com.developer.library.model.Book;
import com.developer.library.model.Transaction;
import com.developer.library.model.TransactionType;
import com.developer.library.model.Users;
import com.developer.library.repository.BookRepo;
import com.developer.library.repository.TransactionRepo;
import com.developer.library.repository.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepo transactionRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BookRepo bookRepo;


    public Transaction borrowBook(Long userId, Long bookId) {
        Users user = userRepo.findById(Math.toIntExact((userId)))
                .orElseThrow(() -> new EntityNotFoundException("User not found."));
        Book book = bookRepo.findById(Math.toIntExact(bookId))
                .orElseThrow(() -> new EntityNotFoundException("Book not found."));

        if (book.getCopiesAvailable() <= 0) {
            throw new IllegalStateException("No copies available.");
        }
        long borrowedBooksCount = transactionRepo.findByUserId(userId).stream()
                .filter(t -> t.getTransactionType() == TransactionType.BORROW)
                .count();
        if (borrowedBooksCount >= 3) {
            throw new IllegalStateException("User has already borrowed 3 books.");
        }

        book.setCopiesAvailable(book.getCopiesAvailable() - 1);
        bookRepo.save(book);

        Transaction transaction = new Transaction();
        transaction.setUserId(userId);
        transaction.setBookId(bookId);
        transaction.setTransactionType((TransactionType.BORROW));
        transaction.setTimeStamp(LocalDateTime.now());
        return transactionRepo.save(transaction);
    }
}
