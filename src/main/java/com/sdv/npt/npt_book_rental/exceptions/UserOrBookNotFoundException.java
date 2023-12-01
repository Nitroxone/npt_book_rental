package com.sdv.npt.npt_book_rental.exceptions;

public class UserOrBookNotFoundException extends Exception {
    public UserOrBookNotFoundException(String message) {
        super(message);
    }
}
