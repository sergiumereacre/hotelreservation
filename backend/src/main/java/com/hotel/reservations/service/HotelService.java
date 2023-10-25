package com.hotel.reservations.service;

import java.util.Date;
import java.util.List;

import com.hotel.reservations.entity.RoomEntity;
import com.hotel.reservations.interfaces.IHotelDetails;
import com.hotel.reservations.entity.HotelEntity;

public class HotelService implements IHotelDetails {

    public String getHotelDetails(){
        return HotelEntity.getInstance().getHotelDetails();
    }

    List<RoomEntity> getRooms(){
        return HotelEntity.getInstance().getRooms();
    }

    RoomEntity getRoomById(int roomId){
        return HotelEntity.getInstance().getRoomById(roomId);
    }

    boolean getRoomIsAvailable(int roomId, Date startDate, Date endDate){
        return HotelEntity.getInstance().getRoomIsAvailable(roomId, startDate, endDate);
    }
    
}
