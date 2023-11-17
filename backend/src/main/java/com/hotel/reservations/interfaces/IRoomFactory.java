package com.hotel.reservations.interfaces;

import com.hotel.reservations.entity.RoomEntity;

public interface IRoomFactory {
    // Registering type
    void registerRoom(RoomEntity room);
    RoomEntity createRoom(String roomType, int roomNumber, Double price);
}
