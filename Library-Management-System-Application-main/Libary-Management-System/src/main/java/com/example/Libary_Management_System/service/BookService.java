package com.example.Libary_Management_System.service;

import com.example.Libary_Management_System.enity.Book;
import com.example.Libary_Management_System.enity.BorrowRecord;
import com.example.Libary_Management_System.enity.Users;
import com.example.Libary_Management_System.exception.BookNotFoundException;
import com.example.Libary_Management_System.repository.BookRepository;
import com.example.Libary_Management_System.repository.BorrowRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BorrowRecordRepository borrowRecordRepository;

    // Update the book by ID
    public ResponseEntity<Book> updateBook(int id, Book book) {
        Optional<Book> existingBookOp = bookRepository.findById(id);
        if(existingBookOp.isEmpty()) {
            throw new BookNotFoundException("Book not found with the id " + id + " Search for another book");
        }
        Book existingBook = existingBookOp.get();

        existingBook.setAuthor(book.getAuthor());
        existingBook.setGenre(book.getGenre());
        existingBook.setTitle(book.getTitle());
        existingBook.setAvailabilityStatus(book.isAvailabilityStatus());
        Book savedBook = bookRepository.save(existingBook);
        return ResponseEntity.status(HttpStatus.OK).body(savedBook);
    }

    // Add the Book
    public ResponseEntity<Book> addBook(Book book) {
        Book savedBook = bookRepository.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }


    // DELETE Book
    public ResponseEntity<String> deleteBook(int id) {
        Optional<Book> existingBookOp = bookRepository.findById(id);
        if(existingBookOp.isEmpty()) {
            throw new BookNotFoundException("Book is not present with the id " + id + " check agian...");
        }

        Book existingBook = existingBookOp.get();
        bookRepository.delete(existingBook);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Book deleted successfully form library...!");
    }

    // borrow book
    public ResponseEntity<BorrowRecord> borrowBook(int id, Users user) {
        // borrowRecord
        Optional<Book> existingBookOp = bookRepository.findById(id);
        if(existingBookOp.isEmpty()) {
            throw new BookNotFoundException("Book not found with id " + id + " borrow another BOOK");
        }

        Book existingBook = existingBookOp.get();
        if(!existingBook.isAvailabilityStatus()) {
            throw new BookNotFoundException(existingBook.getTitle() + " is not available at current moment");
        }

        LocalDate today = LocalDate.now();
        Date borrowDate = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());

        LocalDate returnDateLocal = today.plusDays(7);
        Date returnDate = Date.from(returnDateLocal.atStartOfDay(ZoneId.systemDefault()).toInstant());

        BorrowRecord borrowRecord = new BorrowRecord();
        borrowRecord.setBookId(id);
        borrowRecord.setUserId(user.getId());
        borrowRecord.setReturnDate(borrowDate);
        borrowRecord.setReturnDate(returnDate);

        // add the record to Borrow record
        BorrowRecord savedRecord = borrowRecordRepository.save(borrowRecord);

        // set the book availability as false and save to Book database
        existingBook.setAvailabilityStatus(false);
        bookRepository.save(existingBook);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedRecord);
    }

    public List<Book> allBooks() {
        List<Book> bookList = bookRepository.findAll();
        return bookList;
    }
}
