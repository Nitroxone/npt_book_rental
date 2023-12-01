package com.sdv.npt.npt_book_rental.services;

import java.util.List;

import com.sdv.npt.npt_book_rental.exceptions.IncompleteUserDataException;
import com.sdv.npt.npt_book_rental.exceptions.UserAlreadyExistsException;
import com.sdv.npt.npt_book_rental.exceptions.UserNotFoundException;
import com.sdv.npt.npt_book_rental.model.User;

public interface UserService {
    public void register(User user) throws UserAlreadyExistsException, IncompleteUserDataException;
    public List<User> getUsers();
    public void delete(String id) throws UserNotFoundException;
}
