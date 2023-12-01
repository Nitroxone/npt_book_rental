package com.sdv.npt.npt_book_rental.controllers;

import java.util.Date;
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
import com.sdv.npt.npt_book_rental.model.Book;
import com.sdv.npt.npt_book_rental.repositories.BookRepository;
import com.sdv.npt.npt_book_rental.services.BookService;

@RestController
public class BookController {
    
    @Autowired
    public BookService bookService;
    private BookRepository repo;

    public BookController(BookService bookService, BookRepository repo) {
        this.bookService = bookService;
        this.repo = repo;
    }

    @GetMapping("/list_books")
    public ResponseEntity<String> listAllBooks() {
        String message = bookService.getFormattedBooksList();
        ResponseEntity<String> rep = new ResponseEntity<>(message, HttpStatus.OK);
        return rep;
    }

    @GetMapping("/get_book")
    public ResponseEntity<String> getBook(@RequestParam("id") String id) throws BookNotFoundException{
        String message = bookService.getBookDetails(id);
        return new ResponseEntity<String>(message, HttpStatus.OK);
    }

    @PostMapping("/add_book")
    public ResponseEntity<String> addBook(@RequestBody Book book) throws BookAlreadyExistsException{
        bookService.registerBook(book);

        return new ResponseEntity<String>("Successfully added a book.", HttpStatus.OK);
    }

    @PutMapping("/edit_book")
    public ResponseEntity<String> editBook(@RequestBody Book book) throws BookNotFoundException {
        bookService.editBook(book);
        
        return new ResponseEntity<>("Successfully edited a book.", HttpStatus.OK);
    }

    @DeleteMapping("/remove_book")
    public ResponseEntity<String> removeBook(@RequestParam("id") String id) {

        Optional<Book> book = repo.findById(Long.parseLong(id));
        String message = "";

        if (book.isPresent()) {
            repo.delete(book.get());

            return new ResponseEntity<>(message, HttpStatus.NO_CONTENT);
        } else {
            message = "Could not find book with ID " + id;

            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
}
