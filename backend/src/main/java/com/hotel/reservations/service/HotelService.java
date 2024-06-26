package com.hotel.reservations.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.reservations.entity.RoomEntity;
import com.hotel.reservations.entity.RoomTheme;
import com.hotel.reservations.interfaces.IHotelDetails;
import com.hotel.reservations.repository.ReservationRepository;
import com.hotel.reservations.repository.RoomRepository;
import com.hotel.reservations.entity.HotelEntity;

@Service
public class HotelService implements IHotelDetails {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public String getHotelDetails() {
        return HotelEntity.getInstance().getHotelDetails();
    }

    @Override
    public List<RoomEntity> getRooms() {

        // Get all rooms from the database
        List<RoomEntity> rooms = null;

        try {
            rooms = roomRepository.findAll();
        } catch (Exception e) {
            System.out.println(e);
        }

        return rooms;
    }

    public List<RoomTheme> getAllRoomThemes() {
        return Arrays.asList(RoomTheme.values());
    }

    public void addRoom(RoomEntity room) {
        List<RoomEntity> rooms = getRooms();

        rooms.add(room);
    }

    @Override
    public RoomEntity getRoomById(int roomId) {
        return roomRepository.findByRoomId(roomId).orElse(null);
    }

    @Override
    public boolean getRoomIsAvailable(int roomId, LocalDate startDate, LocalDate endDate) {

        if (startDate.isAfter(endDate)) {
            return false;
        }

        RoomEntity room = getRoomById(roomId);

        // Go through list of reservations and check if the room is available
        // If the room is available, return true
        // If the room is not available, return false

        // try {
        //     reservationRepository.findByRoomAndStartDateLessThanEqualAndEndDateGreaterThanEqual(room, startDate,
        //             endDate);
        // } catch (Exception e) {
        //     System.out.println(e);
        // }

        if (reservationRepository
                .findByRoomAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndIsCancelledFalse(room, startDate, endDate).isPresent()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean getRoomHasCapacity(int roomId, int numGuests) {
        RoomEntity room = getRoomById(roomId);

        if (room != null) {
            return numGuests <= room.getCapacity();
        } else {
            return false;
        }

    }

    @Override
    public List<RoomEntity> getAvailableRooms(LocalDate startDate, LocalDate endDate, int numGuests) {
        // List<RoomEntity> rooms = roomRepository.getAvailableRooms(startDate,
        // endDate);
        List<RoomEntity> rooms = null;

        int totalCapacity = getRoomListCapacity(rooms);

        if (totalCapacity >= numGuests) {
            return rooms;
        } else {
            return null;
        }
    }

    @Override
    public int getTotalAvailableCapacity() {
        List<RoomEntity> rooms = getRooms();

        return getRoomListCapacity(rooms);
    }

    public RoomEntity saveRoom(RoomEntity room) {
        return roomRepository.save(room);
    }

    private int getRoomListCapacity(List<RoomEntity> rooms) {
        int totalCapacity = 0;

        for (RoomEntity room : rooms) {
            totalCapacity += room.getCapacity();
        }

        return totalCapacity;
    }
}
