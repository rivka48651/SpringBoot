package com.mysite.myspringboot.service;

import lombok.RequiredArgsConstructor;
import com.mysite.myspringboot.model.Reservation;
import com.mysite.myspringboot.model.Room;
import com.mysite.myspringboot.model.User;
import org.springframework.stereotype.Service;
import com.mysite.myspringboot.dal.ReservationRepository;
import com.mysite.myspringboot.dal.RoomRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class HotelService {
    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;

    public List<Room> getAvailableRooms(LocalDate checkIn, LocalDate checkOut, int numPeople, boolean wantsJacuzzi) {
        return StreamSupport.stream(roomRepository.findAll().spliterator(), false)
            .filter(r -> r.getCapacity() >= numPeople)
            .filter(r -> !wantsJacuzzi || r.isHasJacuzzi())
            .filter(r -> isRoomAvailable(r, checkIn, checkOut))
            .collect(Collectors.toList());
    }

    public boolean isRoomAvailable(Room room, LocalDate checkIn, LocalDate checkOut) {
        List<Reservation> overlapping = reservationRepository
            .findByRoomAndCheckInDateLessThanEqualAndCheckOutDateGreaterThanEqual(room, checkOut, checkIn);

        return overlapping.isEmpty();
    }

    public void reserveRoom(User user, Room room, LocalDate checkIn, LocalDate checkOut, int numPeople) {
        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setRoom(room);
        reservation.setCheckInDate(checkIn);
        reservation.setCheckOutDate(checkOut);
        reservation.setNumberOfPeople(numPeople);

        reservationRepository.save(reservation);
    }
}
