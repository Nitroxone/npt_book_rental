package com.sdv.npt.npt_book_rental.controllers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sdv.npt.npt_book_rental.exceptions.IncompleteUserDataException;
import com.sdv.npt.npt_book_rental.exceptions.UserAlreadyExistsException;
import com.sdv.npt.npt_book_rental.exceptions.UserNotFoundException;
import com.sdv.npt.npt_book_rental.model.User;
import com.sdv.npt.npt_book_rental.services.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) throws UserAlreadyExistsException, IncompleteUserDataException {
        HttpStatus status = null;

        try {
            userService.register(user);
            status = HttpStatus.NO_CONTENT;
        } catch (UserAlreadyExistsException | IncompleteUserDataException e) {
            status = HttpStatus.CONFLICT;
        }

        return new ResponseEntity<>(status);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> usersList = userService.getUsers();

        return new ResponseEntity<List<User>>(usersList, HttpStatus.OK);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> deleteUser(@RequestParam("id") String id) throws UserNotFoundException {
        HttpStatus status = null;

        try {
            userService.delete(id);
            status = HttpStatus.NO_CONTENT;
        } catch (UserNotFoundException e) {
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(status);
    }
}
