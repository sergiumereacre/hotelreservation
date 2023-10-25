package com.hotel.reservations.interfaces;

public interface IRoomSetting {
    String getRoomTheme();

    double getTemperature();

    int getLighting();

    void setRoomTheme(String theme);

    void setTemperature(double temperature);

    void setLighting(int lighting);
}
