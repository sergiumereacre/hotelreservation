package com.hotel.reservations.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.hotel.reservations.entity.DoubleRoomEntity;
import com.hotel.reservations.entity.FamilyRoomEntity;
import com.hotel.reservations.entity.RoomEntity;
import com.hotel.reservations.entity.SingleRoomEntity;
import com.hotel.reservations.interfaces.IRoomFactory;

// Acts as factory and holds prototype registry
@Component
public class RoomFactory implements IRoomFactory {
    private Map<String, RoomEntity> roomRegistry = new HashMap<>();

    // This is the room that will be cloned from
    public void registerRoom(RoomEntity room) {
        roomRegistry.put(room.getRoomType(), room);
    }

    public RoomEntity createRoom(String type, int roomNumber, Double price) {
        RoomEntity room = (roomRegistry.get(type)).clone();
        room.setPrice(price);
        room.setRoomNumber(roomNumber);

        return room;
    }

    public RoomFactory() {
        initialiseRooms();
    }

    public void initialiseRooms() {
        registerRoom(new SingleRoomEntity(100.0));
        registerRoom(new DoubleRoomEntity(200.0));
        registerRoom(new FamilyRoomEntity(400.0));
    }

    // Return all keys in registry
    public String[] getRoomTypes() {
        return roomRegistry.keySet().toArray(new String[roomRegistry.size()]);
    }
}
