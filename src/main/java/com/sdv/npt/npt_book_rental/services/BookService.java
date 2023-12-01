package com.sdv.npt.npt_book_rental.services;

import java.util.List;

import com.sdv.npt.npt_book_rental.exceptions.BookAlreadyExistsException;
import com.sdv.npt.npt_book_rental.exceptions.BookNotFoundException;
import com.sdv.npt.npt_book_rental.exceptions.NotEnoughBookDataException;
import com.sdv.npt.npt_book_rental.model.Book;

public interface BookService {
    public List<Book> getBooksList();
    public void registerBook(Book book) throws BookAlreadyExistsException;
    public void editBook(Book book) throws BookNotFoundException;
    public void removeBook(String id) throws BookNotFoundException;
    public Book getBookDetails(String id) throws BookNotFoundException;
    public List<Book> search(Book book) throws NotEnoughBookDataException;
}