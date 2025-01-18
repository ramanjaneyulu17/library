package com.developer.library.model;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Entity
@Component
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private long userId;
    private long bookId;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;


    private java.time.LocalDateTime timeStamp;

    public Transaction() {
    }

    public Transaction(int id, long userId, long bookId, TransactionType transactionType, LocalDateTime timeStamp) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.transactionType = transactionType;
        this.timeStamp = timeStamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}

