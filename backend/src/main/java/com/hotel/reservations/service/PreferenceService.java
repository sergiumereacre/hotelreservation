package com.hotel.reservations.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.reservations.entity.ReservationEntity;
import com.hotel.reservations.entity.RoomSettingEntity;
import com.hotel.reservations.entity.RoomTheme;
import com.hotel.reservations.interfaces.IPreferenceMgt;

@Service
public class PreferenceService implements IPreferenceMgt{

    @Autowired
    private ReservationService reservationService;

    @Override
    public void setPreference(String reservationRef, String theme, double temperature, int lighting){
        ReservationEntity reservation = reservationService.getReservation(reservationRef);

        // Could be improved
        if(reservation != null){
            RoomSettingEntity roomSetting = reservation.getRoomSetting();
            roomSetting.setRoomTheme(RoomTheme.valueOf(theme));
            roomSetting.setTemperature(temperature);
            roomSetting.setLighting(lighting);
        }
    }
}
