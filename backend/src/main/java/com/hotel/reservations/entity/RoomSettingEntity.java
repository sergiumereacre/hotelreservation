package com.hotel.reservations.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.hotel.reservations.interfaces.IRoomSetting;

@Entity
public class RoomSettingEntity implements IRoomSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomSettingId;

    private String roomTheme;

    private double temperature;

    private int lighting;

    @Override
    public int getLighting() {
        // TODO Auto-generated method stub
        return this.lighting;
    }

    @Override
    public String getRoomTheme() {
        // TODO Auto-generated method stub
        return this.roomTheme;
    }

    @Override
    public double getTemperature() {
        // TODO Auto-generated method stub
        return this.temperature;
    }

    @Override
    public void setLighting(int lighting) {
        // TODO Auto-generated method stub
        this.lighting = lighting;
    }

    @Override
    public void setRoomTheme(String theme) {
        // TODO Auto-generated method stub
        this.roomTheme = theme;
    }

    @Override
    public void setTemperature(double temperature) {
        // TODO Auto-generated method stub
        this.temperature = temperature;
    }
}
