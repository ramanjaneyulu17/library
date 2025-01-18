package com.developer.library.repository;

import com.developer.library.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction,Integer> {

    List<Transaction> findByUserId(Long userId);
    List<Transaction> findByBookId(Long bookId);
}
