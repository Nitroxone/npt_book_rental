package com.sdv.npt.npt_book_rental.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sdv.npt.npt_book_rental.exceptions.NoExistingRentalException;
import com.sdv.npt.npt_book_rental.exceptions.RentalLimitExceededException;
import com.sdv.npt.npt_book_rental.exceptions.UserNotFoundException;
import com.sdv.npt.npt_book_rental.exceptions.UserOrBookNotFoundException;
import com.sdv.npt.npt_book_rental.model.Rental;
import com.sdv.npt.npt_book_rental.services.RentalService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/rental")
@AllArgsConstructor
public class RentalController {
    
    public RentalService rentalService;

    @PutMapping("/rent")
    public ResponseEntity<String> rent(@RequestParam("userId") String userId, @RequestParam("bookId") String bookId) throws UserOrBookNotFoundException, RentalLimitExceededException {
        HttpStatus status = null;

        try {
            rentalService.rent(userId, bookId);
            status = HttpStatus.NO_CONTENT;
        } catch (UserOrBookNotFoundException e) {
            status = HttpStatus.NOT_FOUND;
        } catch (RentalLimitExceededException e) {
            status = HttpStatus.CONFLICT;
        }

        return new ResponseEntity<>(status);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Rental>> getAllRentals(@RequestParam("userId") String userId) throws UserNotFoundException {
        List<Rental> rentals = null;

        try {
            rentals = rentalService.getRentals(userId);
            return new ResponseEntity<>(rentals, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/give_back")
    public ResponseEntity<String> giveBack(@RequestParam("userId") String userId, @RequestParam("bookId") String bookId) throws UserOrBookNotFoundException, NoExistingRentalException {
        HttpStatus status = null;

        try {
            rentalService.giveBack(userId, bookId);
            status = HttpStatus.NO_CONTENT;
        } catch (UserOrBookNotFoundException | NoExistingRentalException e) {
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(status);
    }
}
