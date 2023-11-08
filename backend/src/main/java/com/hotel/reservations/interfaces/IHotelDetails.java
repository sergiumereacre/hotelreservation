package com.hotel.reservations.interfaces;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.hotel.reservations.entity.RoomEntity;

public interface IHotelDetails {

    String getHotelDetails();

    int getTotalAvailableCapacity();

    List<RoomEntity> getRooms();

    RoomEntity getRoomById(int roomId);

    boolean getRoomIsAvailable(int roomId, LocalDate startDate, LocalDate endDate);

    List<RoomEntity> getAvailableRooms(LocalDate startDate, LocalDate endDate, int numGuests);
    
}
