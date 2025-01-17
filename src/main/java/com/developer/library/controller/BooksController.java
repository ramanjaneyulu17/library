package com.developer.library.controller;

import com.developer.library.model.Books;
import com.developer.library.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BooksController {

    @Autowired
    private BooksService service;

    @GetMapping("/")
    public String welcome(){
        return "Hello Developer, Welcome to Library";
    }

    @GetMapping("/books")
    public List<Books> getAllBooks(){
       return service.getAllBooks();
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<?> findById(@PathVariable int id){
        Optional<Books> book = service.findById(id);
        if(book.isPresent()){
            return new ResponseEntity<>(service.findById(id),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new Books(),HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/books")
    public ResponseEntity<String> addBook(@RequestBody Books book) {
        Optional<Books> addBook=service.findById(book.getId());
        if (addBook.isEmpty()) {
            service.addBook(book);
            return new ResponseEntity<>("added",HttpStatus.OK);
        }else {
            return new ResponseEntity<>("can not added", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<String> updateBook(@PathVariable int id, @RequestBody Books book){
        Optional<Books> books=service.findById(id);
        if(books.isEmpty()){
           return new ResponseEntity<>("Not Found",HttpStatus.NOT_FOUND);
        }else {
            service.updateBook(id,book);
            return new ResponseEntity<>("Updated",HttpStatus.OK);
        }
    }
    @DeleteMapping("/books/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id){
        Optional<Books> deleteBook=service.findById(id);
        if(deleteBook.isPresent()) {
            service.deleteBook(id);
            return new ResponseEntity<>("Deleted",HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Not found",HttpStatus.NOT_FOUND);
        }
    }
}
