package com.sdv.npt.npt_book_rental.repositories;

import com.sdv.npt.npt_book_rental.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByEmail(String email);
}
