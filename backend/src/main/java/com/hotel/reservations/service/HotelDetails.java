package com.hotel.reservations.service;

import java.util.Date;
import java.util.List;

import com.hotel.reservations.entity.Room;
import com.hotel.reservations.entity.Hotel;

public class HotelDetails {

    String getHotelDetails(){
        return Hotel.getInstance().getHotelDetails();
    }

    List<Room> getRooms(){
        return Hotel.getInstance().getRooms();
    }

    Room getRoomById(int roomId){
        return Hotel.getInstance().getRoomById(roomId);
    }

    boolean getRoomIsAvailable(int roomId, Date startDate, Date endDate){
        return Hotel.getInstance().getRoomIsAvailable(roomId, startDate, endDate);
    }
    
}
