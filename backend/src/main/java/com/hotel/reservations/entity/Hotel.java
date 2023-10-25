package com.hotel.reservations.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hotel.reservations.interfaces.IHotelDetails;

public class Hotel implements IHotelDetails{
    private static Hotel hotelInstance;
    private List<Room> rooms;
    private String hotelDetails = "Hotel Name: Star-Crossed Hotel\n" +
            "Hotel Address: Groningen, 1234 Sesame Street, Star-Crossed Hotel\n" +
            "Hotel Phone: 123-456-7890\n" +
            "Hotel Email: starcrossedhotel@gmail.com";

    private Hotel() {
        rooms = new ArrayList<>();
    }

    public static Hotel getInstance() {
        if (hotelInstance == null) {
            hotelInstance = new Hotel();
        }
        return hotelInstance;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Room getRoomById(int roomId) {
        for (Room room : rooms) {
            if (room.getRoomId() == roomId) {
                return room;
            }
        }
        return null;
    }

    public String getHotelDetails(){
        return hotelDetails;
    }

    public List<Room> getRooms(){
        return rooms;
    }

    public boolean getRoomIsAvailable(int roomId, Date startDate, Date endDate){

    }
}