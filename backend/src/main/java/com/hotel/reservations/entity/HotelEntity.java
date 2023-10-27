package com.hotel.reservations.entity;

import java.util.ArrayList;
import java.util.List;

public class HotelEntity {
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

    public String getHotelDetails() {
        return hotelDetails;
    }

    public List<RoomEntity> getRooms() {
        return rooms;
    }
}