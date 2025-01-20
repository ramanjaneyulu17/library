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
import java.util.List;
import java.util.Optional;

import static com.developer.library.model.TransactionType.*;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepo transactionRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BookRepo bookRepo;


    public Transaction borrowBook(long userId, long bookId) {
        Users user = userRepo.findById(Math.toIntExact((userId)))
                .orElseThrow(() -> new EntityNotFoundException("User not found."));
        Book book = bookRepo.findById(Math.toIntExact(bookId))
                .orElseThrow(() -> new EntityNotFoundException("Book not found."));

        if (book.getCopiesAvailable() <= 0) {
            throw new IllegalStateException("No copies available.");
        }
        int borrowedBooksCount = (int) transactionRepo.findByUserId(Math.toIntExact(userId)).stream()
                .filter(t -> t.getTransactionType() == BORROW)
                .count();
        if (borrowedBooksCount >= 3) {
            throw new IllegalStateException("User has already borrowed 3 books.");
        }

        book.setCopiesAvailable(book.getCopiesAvailable() - 1);
        bookRepo.save(book);

        Transaction transaction = new Transaction();
        transaction.setUserId(userId);
        transaction.setBookId(bookId);
        transaction.setTransactionType((BORROW));
        transaction.setTimeStamp(LocalDateTime.now());
        return transactionRepo.save(transaction);
    }

    public Transaction returnBook(Long userId, Long bookId) {
        Transaction borrowTransaction = transactionRepo.findByUserId(Math.toIntExact(userId)).stream()
                .filter(tx -> tx.getBookId()==bookId && tx.getTransactionType() == TransactionType.BORROW)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Book was not borrowed by this user."));

        Book book = bookRepo.findById(Math.toIntExact(bookId))
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        book.setCopiesAvailable(book.getCopiesAvailable() + 1);
        bookRepo.save(book);

        Transaction transaction = new Transaction();
        transaction.setUserId(userId);
        transaction.setBookId(bookId);
        transaction.setTransactionType(TransactionType.RETURN);
        transaction.setTimeStamp(LocalDateTime.now());
        return transactionRepo.save(transaction);
    }

    public List<Transaction> getTransactionsByUser(Long userId) {

        if (!userRepo.existsById(Math.toIntExact(userId))) {
            throw new IllegalArgumentException("User not found.");
        }

        return transactionRepo.findByUserId(Math.toIntExact(userId));
    }

    public List<Transaction> getTransactionsByBook(Long bookId) {

        if (!bookRepo.existsById(Math.toIntExact(bookId))) {
            throw new IllegalArgumentException("Book not found.");
        }

        return transactionRepo.findByBookId(Math.toIntExact(bookId));
    }
}
