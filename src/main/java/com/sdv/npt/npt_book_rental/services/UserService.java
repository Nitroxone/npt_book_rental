package com.sdv.npt.npt_book_rental.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sdv.npt.npt_book_rental.repositories.UserRepository;
import com.sdv.npt.npt_book_rental.model.User;

@Service
public class UserService {
 
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Encodes the provided User's password and saves them to the database.
     * @param user the User to save
     * @return the saved User
     */
    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
