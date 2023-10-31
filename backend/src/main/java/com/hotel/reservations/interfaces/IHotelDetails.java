package com.hotel.reservations.interfaces;

import java.util.Date;
import java.util.List;

import com.hotel.reservations.entity.RoomEntity;

public interface IHotelDetails {

    String getHotelDetails();

    List<RoomEntity> getRooms();

    RoomEntity getRoomById(int roomId);

    boolean getRoomIsAvailable(int roomId, Date startDate, Date endDate);
    
}
