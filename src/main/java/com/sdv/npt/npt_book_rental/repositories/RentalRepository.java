package com.sdv.npt.npt_book_rental.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sdv.npt.npt_book_rental.model.Book;
import com.sdv.npt.npt_book_rental.model.Rental;
import com.sdv.npt.npt_book_rental.model.User;

public interface RentalRepository extends JpaRepository<Rental, Long> {
    public List<Rental> findByRentedBy(User rentedBy);
    public List<Rental> findByRentedBook(Book rentedBook);
    public Optional<Rental> findOneByRentedByAndRentedBook(User rentedBy, Book rentedBook);
}
