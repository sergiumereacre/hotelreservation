package com.hotel.reservations.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.reservations.entity.RoomEntity;
import com.hotel.reservations.entity.RoomTheme;
import com.hotel.reservations.interfaces.IHotelDetails;
import com.hotel.reservations.repository.RoomRepository;
import com.hotel.reservations.entity.HotelEntity;

@Service
public class HotelService implements IHotelDetails {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public String getHotelDetails() {
        return HotelEntity.getInstance().getHotelDetails();
    }

    @Override
    public List<RoomEntity> getRooms() {

        // Get all rooms from the database
        return roomRepository.findAll();
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
    public boolean getRoomIsAvailable(int roomId, Date startDate, Date endDate) {

        if (startDate.after(endDate)) {
            return false;
        }

        // Go through list of reservations and check if the room is available
        // If the room is available, return true
        // If the room is not available, return false
        return false;
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
    public List<RoomEntity> getAvailableRooms(Date startDate, Date endDate, int numGuests) {
        // List<RoomEntity> rooms = roomRepository.getAvailableRooms(startDate, endDate);
        List<RoomEntity> rooms = null;

        int totalCapacity = getRoomListCapacity(rooms);

        if(totalCapacity >= numGuests){
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
