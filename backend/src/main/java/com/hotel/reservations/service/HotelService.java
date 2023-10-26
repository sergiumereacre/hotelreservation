package com.hotel.reservations.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hotel.reservations.entity.RoomEntity;
import com.hotel.reservations.interfaces.IHotelDetails;
import com.hotel.reservations.entity.HotelEntity;

@Service
public class HotelService implements IHotelDetails {

    public String getHotelDetails(){
        return HotelEntity.getInstance().getHotelDetails();
    }

    public List<RoomEntity> getRooms(){
        return HotelEntity.getInstance().getRooms();
    }

    public RoomEntity getRoomById(int roomId){
        return HotelEntity.getInstance().getRoomById(roomId);
    }

    public boolean getRoomIsAvailable(int roomId, Date startDate, Date endDate){
        return HotelEntity.getInstance().getRoomIsAvailable(roomId, startDate, endDate);
    }
    
}
