package com.sdv.npt.npt_book_rental.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.sdv.npt.npt_book_rental.exceptions.NoExistingRentalException;
import com.sdv.npt.npt_book_rental.exceptions.RentalLimitExceededException;
import com.sdv.npt.npt_book_rental.exceptions.UserNotFoundException;
import com.sdv.npt.npt_book_rental.exceptions.UserOrBookNotFoundException;
import com.sdv.npt.npt_book_rental.model.Book;
import com.sdv.npt.npt_book_rental.model.Rental;
import com.sdv.npt.npt_book_rental.model.User;
import org.springframework.stereotype.Service;

import com.sdv.npt.npt_book_rental.repositories.BookRepository;
import com.sdv.npt.npt_book_rental.repositories.RentalRepository;
import com.sdv.npt.npt_book_rental.repositories.UserRepository;

@Service
public class RentalServiceImpl implements RentalService {
    
    private RentalRepository repo;
    private BookRepository bookRepo;
    private UserRepository userRepo;

    public RentalServiceImpl(RentalRepository repo, BookRepository bookRepo, UserRepository userRepo) {
        this.repo = repo;
        this.bookRepo = bookRepo;
        this.userRepo = userRepo;
    }

    public void rent(String userId, String bookId) throws UserOrBookNotFoundException, RentalLimitExceededException {
        Optional<User> user = userRepo.findById(Long.parseLong(userId));
        Optional<Book> book = bookRepo.findById(Long.parseLong(bookId));

        if (!user.isPresent() || !book.isPresent()) {
            throw new UserOrBookNotFoundException("User or book not found");
        }
        else if (repo.findByRentedBy(user.get()).size() >= Rental.RENTAL_LIMIT) {
            throw new RentalLimitExceededException("User has reached rental limit");
        }

        LocalDate date = LocalDate.now();
        date = date.plusDays(30);
        Rental rental = new Rental();
        rental.setRentedBy(user.get());
        rental.setRentedBook(book.get());
        rental.setReturnDate(date);
        repo.save(rental);
    }

    public List<Rental> getRentals(String userId) throws UserNotFoundException {
        Optional<User> user = userRepo.findById(Long.parseLong(userId));

        if (!user.isPresent()) {
            throw new UserNotFoundException("User not found with ID " + userId);
        }

        return repo.findByRentedBy(user.get());
    }

    public void giveBack(String userId, String bookId) throws UserOrBookNotFoundException, NoExistingRentalException {
        Optional<User> user = userRepo.findById(Long.parseLong(userId));
        Optional<Book> book = bookRepo.findById(Long.parseLong(bookId));

        if (!user.isPresent() || !book.isPresent()) {
            throw new UserOrBookNotFoundException("User or book not found");
        }

        Optional<Rental> rental = repo.findOneByRentedByAndRentedBook(user.get(), book.get()); // Extremely verbose getter name...
        if (!rental.isPresent()) {
            throw new NoExistingRentalException("No registered rental matches the provided User and Book");
        }

        repo.delete(rental.get());
    }
}
