package com.developer.library.repository;

import com.developer.library.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionsRepo extends JpaRepository<Transactions,Integer> {
}
