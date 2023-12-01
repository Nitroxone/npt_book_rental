package com.sdv.npt.npt_book_rental.exceptions;

public class RentalLimitExceededException extends Exception {
    public RentalLimitExceededException(String message) {
        super(message);
    }
}
