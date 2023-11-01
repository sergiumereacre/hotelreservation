package com.hotel.reservations.controller;

import java.sql.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.reservations.entity.RoomEntity;
import com.hotel.reservations.entity.RoomTheme;
import com.hotel.reservations.service.HotelService;


@RequestMapping("/hotel")
@RestController
public class HotelController {
    
    @Autowired
    private HotelService service;

    @GetMapping("/details")
    public ResponseEntity<String> getHotelDetails() {
        return ResponseEntity.ok(service.getHotelDetails());
    }

    @GetMapping("/rooms")
    public ResponseEntity<List<RoomEntity>> getRooms() {
        return ResponseEntity.ok(service.getRooms());
    }
    
    // Get room by id
    @GetMapping("/room/{roomId}")
    public ResponseEntity<RoomEntity> getRoomById(int roomId) {
        return ResponseEntity.ok(service.getRoomById(roomId));
    }

    // Check if room is available
    @GetMapping("/room/{roomId}/is-available")
    public ResponseEntity<Boolean> getRoomIsAvailable(int roomId, Date startDate, Date endDate) {
        return ResponseEntity.ok(service.getRoomIsAvailable(roomId, startDate, endDate));
    }

    // Get all available rooms between dates and can accommodate number of guests
    @PostMapping("/rooms/available")
    public ResponseEntity<List<RoomEntity>> getAvailableRooms(Date startDate, Date endDate, int numGuests) {
        return ResponseEntity.ok(service.getAvailableRooms(startDate, endDate, numGuests));
    }

    // Get all themes
    @GetMapping("/themes")
    public ResponseEntity<List<RoomTheme>> getAllThemes() {
        return ResponseEntity.ok(service.getAllRoomThemes());
    }

    @PostMapping("/rooms")
    public ResponseEntity<RoomEntity> createRoom(@Valid @RequestBody RoomEntity room) {
        return ResponseEntity.ok(service.saveRoom(room));
    }
}
