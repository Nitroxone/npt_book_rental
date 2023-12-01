package com.sdv.npt.npt_book_rental.services;

import java.util.List;
import java.util.Optional;

import com.sdv.npt.npt_book_rental.model.User;
import org.springframework.stereotype.Service;

import com.sdv.npt.npt_book_rental.exceptions.IncompleteUserDataException;
import com.sdv.npt.npt_book_rental.exceptions.UserAlreadyExistsException;
import com.sdv.npt.npt_book_rental.exceptions.UserNotFoundException;
import com.sdv.npt.npt_book_rental.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    
    private UserRepository repo;

    public UserServiceImpl(UserRepository repo) {
        this.repo = repo;
    }

    public void register(User user) throws UserAlreadyExistsException, IncompleteUserDataException {
        List<User> found = repo.findByUsername(user.getUsername());
        if (!found.isEmpty()) {
            throw new UserAlreadyExistsException("A user with username " + user.getUsername() + " already exists.");
        } else if (user.getUsername() == null 
                || user.getEmail() == null 
                || user.getPassword() == null) {
                    throw new IncompleteUserDataException("Attempted to register a new user with insufficient data.");
        }

        repo.save(user);
    }

    public List<User> getUsers() {
        List<User> users = repo.findAll();

        return users;
    }

    public void delete(String id) throws UserNotFoundException {
        Optional<User> user = repo.findById(Long.parseLong(id));

        if (user.isPresent()) {
            repo.delete(user.get());
        } else throw new UserNotFoundException("User with ID " + id + " does not exist.");
    }
}
