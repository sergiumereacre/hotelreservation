package com.hotel.reservations.interfaces;

import com.hotel.reservations.entity.RoomTheme;

public interface IRoomSetting {
    RoomTheme getRoomTheme();

    double getTemperature();

    int getLighting();

    void setRoomTheme(RoomTheme theme);

    void setTemperature(double temperature);

    void setLighting(int lighting);
}
