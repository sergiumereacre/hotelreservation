package com.hotel.reservations.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hotel.reservations.interfaces.IHotelDetails;

public class HotelEntity implements IHotelDetails{
    private static HotelEntity hotelInstance;
    
    private List<RoomEntity> rooms;
    
    
    private String hotelDetails = "Hotel Name: Star-Crossed Hotel\n" +
            "Hotel Address: Groningen, 1234 Sesame Street, Star-Crossed Hotel\n" +
            "Hotel Phone: 123-456-7890\n" +
            "Hotel Email: starcrossedhotel@gmail.com";

    private HotelEntity() {
        rooms = new ArrayList<>();
    }

    public static HotelEntity getInstance() {
        if (hotelInstance == null) {
            hotelInstance = new HotelEntity();
        }
        return hotelInstance;
    }

    public void addRoom(RoomEntity room) {
        rooms.add(room);
    }

    public RoomEntity getRoomById(int roomId) {
        for (RoomEntity room : rooms) {
            if (room.getRoomId() == roomId) {
                return room;
            }
        }
        return null;
    }

    public String getHotelDetails(){
        return hotelDetails;
    }

    public List<RoomEntity> getRooms(){
        return rooms;
    }

    public boolean getRoomIsAvailable(int roomId, Date startDate, Date endDate){
        // Go through list of reservations and check if the room is available
        // If the room is available, return true
        // If the room is not available, return false
        return false;
    }
}