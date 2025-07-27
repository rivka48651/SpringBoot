package com.mysite.myspringboot.controllers;

import com.mysite.myspringboot.dto.ReservationDTO;
import com.mysite.myspringboot.dto.RoomDTO;
import com.mysite.myspringboot.model.Room;
import com.mysite.myspringboot.model.User;
import com.mysite.myspringboot.model.Reservation;
import com.mysite.myspringboot.service.HotelService;
import com.mysite.myspringboot.dal.RoomRepository;
import com.mysite.myspringboot.dal.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/hotel")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    @GetMapping("/available-rooms")
    public List<RoomDTO> getAvailableRooms(
            @RequestParam LocalDate checkIn,
            @RequestParam LocalDate checkOut,
            @RequestParam int numPeople,
            @RequestParam boolean wantsJacuzzi) {

        return hotelService.getAvailableRooms(checkIn, checkOut, numPeople, wantsJacuzzi)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/reserve")
    public String reserveRoom(@RequestBody ReservationDTO dto) {
        Room room = roomRepository.findByRoomNumber(dto.getRoomNumber());
        User user = userRepository.findById(dto.getUserId()).orElse(null);

        if (room == null || user == null) {
            return "Room or user not found";
        }

        if (!hotelService.isRoomAvailable(room, dto.getCheckInDate(), dto.getCheckOutDate())) {
            return "Room is not available";
        }

        hotelService.reserveRoom(user, room, dto.getCheckInDate(), dto.getCheckOutDate(), dto.getNumberOfPeople());
        return "Reservation successful";
    }

    private RoomDTO convertToDTO(Room room) {
        RoomDTO dto = new RoomDTO();
        dto.setRoomNumber(room.getRoomNumber());
        dto.setType(room.getType());
        dto.setCapacity(room.getCapacity());
        dto.setHasJacuzzi(room.isHasJacuzzi());
        return dto;
    }
}
