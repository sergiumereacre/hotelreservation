package com.hotel.reservations.service;

import org.springframework.stereotype.Service;

import com.hotel.reservations.entity.RoomTheme;
import com.hotel.reservations.interfaces.IRoomSetting;

@Service
public class RoomSettingService implements IRoomSetting{

    @Override
    public int getLighting() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public RoomTheme getRoomTheme() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public double getTemperature() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setLighting(int lighting) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setRoomTheme(RoomTheme theme) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setTemperature(double temperature) {
        // TODO Auto-generated method stub
        
    }
}
