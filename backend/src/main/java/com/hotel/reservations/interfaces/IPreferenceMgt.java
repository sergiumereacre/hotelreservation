package com.hotel.reservations.interfaces;

import com.hotel.reservations.entity.ReservationEntity;

public interface IPreferenceMgt {
    void setPreference(ReservationEntity reservation, String theme, double temperature, int lighting);
}
