package com.hotel.reservations.entity;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private static Hotel hotelInstance;
    private List<Room> rooms;

    private Hotel() {
        rooms = new ArrayList<>();
    }

    public static Hotel getInstance() {
        if (hotelInstance == null) {
            hotelInstance = new Hotel();
        }
        return hotelInstance;
    }
}