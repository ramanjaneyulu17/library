package com.developer.library.repository;

import com.developer.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Book,Integer>{

    @Query("SELECT p from Book p WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(p.author) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(p.ISBN) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Book> findByKeywordContainingIgnoreCase(String keyword);

    @Query("SELECT b.copiesAvailable FROM Book b WHERE b.id = :id")
    int findCopiesAvailableById(int id);
}
