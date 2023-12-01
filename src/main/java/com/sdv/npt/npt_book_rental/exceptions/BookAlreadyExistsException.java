package com.sdv.npt.npt_book_rental.exceptions;

public class BookAlreadyExistsException extends Exception {
    public BookAlreadyExistsException(String message) {
        super(message);
    }
}
