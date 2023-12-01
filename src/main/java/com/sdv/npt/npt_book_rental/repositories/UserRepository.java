package com.sdv.npt.npt_book_rental.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sdv.npt.npt_book_rental.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    public List<User> findByUsername(String username);
}
