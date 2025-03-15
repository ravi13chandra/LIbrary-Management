package com.example.Libary_Management_System.controller;

import com.example.Libary_Management_System.enity.BorrowRecord;
import com.example.Libary_Management_System.enity.Users;
import com.example.Libary_Management_System.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
public class BorrowRecordController {

    @Autowired
    private BookService bookService;

    // Borrow book
    @PostMapping(path = "/member/borrow/{id}")
    public ResponseEntity<BorrowRecord> borrowBook(@PathVariable int id, @RequestBody Users user) {
        return bookService.borrowBook(id, user);
    }
}
