package com.mysite.myspringboot.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data

public class Reservation {

    @Id
    @GeneratedValue
    private int NumOfReservation;
    @ManyToOne
    private User user;

    @ManyToOne
    private Room room;

    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private int numberOfPeople;
}
