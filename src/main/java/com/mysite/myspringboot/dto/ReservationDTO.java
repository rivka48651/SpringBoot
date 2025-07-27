package com.mysite.myspringboot.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservationDTO {
    private int roomNumber;
    private int userId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private int numberOfPeople;
}

