package com.developer.library.repository;

import com.developer.library.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BooksRepo extends JpaRepository<Books,Integer>{

    @Query("SELECT p from Books p WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(p.author) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(p.ISBN) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Books> searchBooks(String keyword);
}
