package com.sdv.npt.npt_book_rental.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sdv.npt.npt_book_rental.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    public List<Book> findByName(String name);
}
