package com.hotel.reservations.interfaces;

import java.util.Date;
import java.util.List;

import com.hotel.reservations.entity.Room;

public interface IHotelDetails {

    String getHotelDetails();

    List<Room> getRooms();

    String getRoomDetails(int roomId);

    boolean getRoomIsAvailable(int roomId, Date startDate, Date endDate);
    
}
