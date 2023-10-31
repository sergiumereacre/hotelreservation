package com.hotel.reservations.service;

import org.springframework.stereotype.Service;

import com.hotel.reservations.entity.ReservationEntity;
import com.hotel.reservations.interfaces.IPreferenceMgt;

@Service
public class PreferenceService implements IPreferenceMgt{

    @Override
    public void setPreference(ReservationEntity reservation, String theme, double temperature, int lighting){

    }
}
