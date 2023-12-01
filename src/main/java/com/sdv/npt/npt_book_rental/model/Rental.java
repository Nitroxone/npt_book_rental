package com.sdv.npt.npt_book_rental.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
@Table(name="rental")
@Entity
public class Rental {

    public static final int RENTAL_LIMIT = 5;
    
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User rentedBy;

    @ManyToOne
    private Book rentedBook;

    private LocalDate returnDate;
}
