package com.hotel.reservations.service;

import com.hotel.reservations.interfaces.IEngageReservation;

public class EngageReservation implements IEngageReservation{
    public boolean doCheckIn(String reservationRef){
        // 1. Get reservation 
        // 2. Set reservation isClaimed to true

        return true;
    }

    public boolean doCheckOut(String reservationRef){
        // 1. Get reservation 
        // 2. Set reservation isClaimed to false

        return true;
    }
}
