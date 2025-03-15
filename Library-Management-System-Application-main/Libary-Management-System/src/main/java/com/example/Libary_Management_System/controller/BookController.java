package com.example.Libary_Management_System.controller;

import com.example.Libary_Management_System.enity.Book;
import com.example.Libary_Management_System.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class BookController {

    @Autowired
    private BookService bookService;

    // add new book POST
    @PostMapping(path = "/librarian/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    // update the book details PUT
    @PutMapping(path = "/librarian/books/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable int id, Book book) {
        return bookService.updateBook(id, book);
    }

    // delete the books DELETE
    @DeleteMapping(path = "/librarian/books/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id) {
        return bookService.deleteBook(id);
    }

    @GetMapping(path = "/member/books")
    public List<Book> allBooks() {
        return bookService.allBooks();
    }
}
