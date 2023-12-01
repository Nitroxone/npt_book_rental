package com.sdv.npt.npt_book_rental.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sdv.npt.npt_book_rental.exceptions.BookAlreadyExistsException;
import com.sdv.npt.npt_book_rental.exceptions.BookNotFoundException;
import com.sdv.npt.npt_book_rental.exceptions.NotEnoughBookDataException;
import com.sdv.npt.npt_book_rental.model.Book;
import com.sdv.npt.npt_book_rental.repositories.BookRepository;

@Service
public class BookServiceImpl implements BookService {
    
    private BookRepository repo;

    public BookServiceImpl(BookRepository repo) {
        this.repo = repo;
    }

    /**
     * Generates a String that contains formatted info about all registered books in the database and returns it.
     * @return a formatted String
     */
    public List<Book> getBooksList() {
        List<Book> books = repo.findAll();

        return books;
    }

    public void registerBook(Book book) throws BookAlreadyExistsException {
        List<Book> found = repo.findByName(book.getName());
        if (!found.isEmpty()) {
            throw new BookAlreadyExistsException("Tried to add a book whose name is already registered.");
        }

        book.setPublishDate(new Date());
        if (book.getAmount() == null) book.setAmount(Book.DEFAULT_BOOK_AMOUNT);
        repo.save(book);
    }

    public void editBook(Book book) throws BookNotFoundException {
        Optional<Book> foundBook = repo.findById(book.getId());
        if (!foundBook.isPresent()) {
            throw new BookNotFoundException("Tried to edit a book that doesn't exist.");
        }

        if (book.getName() != null) foundBook.get().setName(book.getName());
        if (book.getAuthor() != null) foundBook.get().setAuthor(book.getAuthor());
        if (book.getAmount() != null) foundBook.get().setAmount(book.getAmount());

        repo.save(foundBook.get());
    }

    public void removeBook(String id) throws BookNotFoundException {
        Optional<Book> foundBook = repo.findById(Long.parseLong(id));
        if (foundBook.isPresent()) {
            repo.delete(foundBook.get());
        } else throw new BookNotFoundException("No book found with ID " + id);
    }

    public Book getBookDetails(String id) throws BookNotFoundException {
        Optional<Book> foundBook = repo.findById(Long.parseLong(id));
        if (!foundBook.isPresent()) {
            throw new BookNotFoundException("Tried to retrieve a book that doesn't exist.");
        }

        return foundBook.get();
    }

    public List<Book> search(Book book) throws NotEnoughBookDataException {
        List<Book> results = null;
        
        if (book.getName() != null) {
            if (book.getAuthor() != null) results = repo.findByNameAndAuthor(book.getName(), book.getAuthor());
            else results = repo.findByName(book.getName());
        } else if (book.getAuthor() != null) {
            results = repo.findByAuthor(book.getAuthor());
        } else {
            throw new NotEnoughBookDataException("Too little search data was provided to find a book.");
        }

        return results;
    }
}
