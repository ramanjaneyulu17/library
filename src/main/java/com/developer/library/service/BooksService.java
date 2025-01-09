package com.developer.library.service;

import com.developer.library.model.Books;
import com.developer.library.repository.BooksRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BooksService {

    @Autowired
    private BooksRepo repo;

    public Books addBook(Books book) {
        return repo.save(book);
    }

    public List<Books> getAllBooks() {
        return repo.findAll();
    }

    public Optional<Books> findById(int id) {
        return repo.findById(id);
    }

    public void updateBook(Books book) {
            repo.save(book);
    }

    public void deleteBook(int id) {
        repo.deleteById(id);
    }
}
