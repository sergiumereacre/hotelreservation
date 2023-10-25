package com.hotel.reservations.interfaces;

import com.hotel.reservations.entity.Reservation;

public interface IPreferenceMgt {
    void setPreference(Reservation reservation, String theme, double temperature, int lighting);
}
