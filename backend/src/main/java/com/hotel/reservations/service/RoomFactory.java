package com.hotel.reservations.service;

import java.util.HashMap;
import java.util.Map;

import com.hotel.reservations.entity.RoomEntity;
import com.hotel.reservations.interfaces.IRoomFactory;

// Acts as factory and holds prototype registry
public class RoomFactory implements IRoomFactory {
    private Map<String, RoomEntity> roomRegistry = new HashMap<>();

    // This is the room that will be cloned from
    public void registerRoom(RoomEntity room) {
        roomRegistry.put(room.getRoomType(), room);
    }

    public RoomEntity createRoom(String type, Double price) {
        RoomEntity room = (roomRegistry.get(type)).clone();

        room.setPrice(price);

        return room;
    }
}
