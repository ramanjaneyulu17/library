package com.developer.library.service;

import com.developer.library.model.Book;
import com.developer.library.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BooksService {

    @Autowired
    private BookRepo repo;

    public Book addBook(Book book) {
        return repo.save(book);
    }

    public List<Book> getAllBooks() {
        return repo.findAll();
    }

    public Optional<Book> findById(int id) {
        return repo.findById(id);
    }

    public void updateBook(int id, Book book) {
            book.setId(id);
            repo.save(book);
    }

    public void deleteBook(int id) {
        repo.deleteById(id);
    }

    public List<Book> searchBooks(String keyword) {
        return repo.findByKeywordContainingIgnoreCase(keyword);
    }

    public int findCopiesAvailableById(int id) {
       return repo.findCopiesAvailableById(id);
    }
}
