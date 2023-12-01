package com.sdv.npt.npt_book_rental.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Table(name="user")
@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
}
