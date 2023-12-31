package com.sdv.npt.npt_book_rental.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "book")
@Entity
public class Book {
    
    public static final int DEFAULT_BOOK_AMOUNT = 50;

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String author;
    private Date publishDate;
    @Column(nullable = true)
    private Integer amount;
}
