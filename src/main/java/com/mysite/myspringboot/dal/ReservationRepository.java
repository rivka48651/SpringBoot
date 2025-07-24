package com.mysite.myspringboot.dal;

import java.time.LocalDate;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mysite.myspringboot.model.Reservation;
import com.mysite.myspringboot.model.Room;


import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation,Integer> {
   List<Reservation> findByRoomAndCheckInDateLessThanEqualAndCheckOutDateGreaterThanEqual(
        Room room, LocalDate checkOut, LocalDate checkIn);
    
}
