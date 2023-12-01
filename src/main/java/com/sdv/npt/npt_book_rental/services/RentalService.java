package com.sdv.npt.npt_book_rental.services;

import java.util.List;

import com.sdv.npt.npt_book_rental.exceptions.NoExistingRentalException;
import com.sdv.npt.npt_book_rental.exceptions.RentalLimitExceededException;
import com.sdv.npt.npt_book_rental.exceptions.UserNotFoundException;
import com.sdv.npt.npt_book_rental.exceptions.UserOrBookNotFoundException;
import com.sdv.npt.npt_book_rental.model.Rental;

public interface RentalService {
    public void rent(String userId, String bookId) throws UserOrBookNotFoundException, RentalLimitExceededException;
    public List<Rental> getRentals(String userId) throws UserNotFoundException;
    public void giveBack(String userId, String bookId) throws UserOrBookNotFoundException, NoExistingRentalException;
}
