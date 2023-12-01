package com.sdv.npt.npt_book_rental.controllers;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sdv.npt.npt_book_rental.exceptions.BookAlreadyExistsException;
import com.sdv.npt.npt_book_rental.exceptions.BookNotFoundException;
import com.sdv.npt.npt_book_rental.exceptions.NotEnoughBookDataException;
import com.sdv.npt.npt_book_rental.model.Book;
import com.sdv.npt.npt_book_rental.services.BookService;

@RestController
public class BookController {
    
    @Autowired
    public BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/list_books")
    public ResponseEntity<List<Book>> listAllBooks() {
        List<Book> booksList = bookService.getBooksList();
    
        return new ResponseEntity<List<Book>>(booksList, HttpStatus.OK);
    }

    @GetMapping("/get_book")
    public ResponseEntity<Book> getBook(@RequestParam("id") String id) throws BookNotFoundException {
        Book book = null;

        try {
            book = bookService.getBookDetails(id);
            return new ResponseEntity<Book>(book, HttpStatus.OK);
        } catch (BookNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/search_book")
    public ResponseEntity<List<Book>> searchBook(@RequestBody Book book) throws NotEnoughBookDataException {
        List<Book> books = null;

        try {
            books = bookService.search(book);
            return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
        } catch (NotEnoughBookDataException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add_book")
    public ResponseEntity<String> addBook(@RequestBody Book book) throws BookAlreadyExistsException {
        HttpStatus status = null;

        try {
            bookService.registerBook(book);
            status = HttpStatus.NO_CONTENT;
        } catch (BookAlreadyExistsException e) {
            status = HttpStatus.CONFLICT;
        }

        return new ResponseEntity<>(status);
    }

    @PutMapping("/edit_book")
    public ResponseEntity<String> editBook(@RequestBody Book book) throws BookNotFoundException {
        HttpStatus status = null;

        try {
            bookService.editBook(book);
            status = HttpStatus.NO_CONTENT;
        } catch (BookNotFoundException e) {
            status = HttpStatus.NOT_FOUND;
        }
        
        return new ResponseEntity<>(status);
    }

    @DeleteMapping("/remove_book")
    public ResponseEntity<String> removeBook(@RequestParam("id") String id) throws BookNotFoundException {
        HttpStatus status = null;

        try {
            bookService.removeBook(id);
            status = HttpStatus.NO_CONTENT;
        } catch (BookNotFoundException e) {
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(status);
    }
}
