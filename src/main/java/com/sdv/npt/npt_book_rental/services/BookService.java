package com.sdv.npt.npt_book_rental.services;

import com.sdv.npt.npt_book_rental.exceptions.BookAlreadyExistsException;
import com.sdv.npt.npt_book_rental.exceptions.BookNotFoundException;
import com.sdv.npt.npt_book_rental.model.Book;

public interface BookService {
    public String getFormattedBooksList();
    public void registerBook(Book book) throws BookAlreadyExistsException;
    public void editBook(Book book) throws BookNotFoundException;
    public String getBookDetails(String id) throws BookNotFoundException;
}