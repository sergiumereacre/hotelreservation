package com.hotel.reservations.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.hotel.reservations.interfaces.IRoomSetting;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class RoomSettingEntity implements IRoomSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomSettingId;

    private RoomTheme roomTheme = RoomTheme.DEFAULT;

    private double temperature = -1;

    private int lighting = -1;

    

    @Override
    public int getLighting() {
        return this.lighting;
    }

    @Override
    public RoomTheme getRoomTheme() {
        return this.roomTheme;
    }

    @Override
    public double getTemperature() {
        return this.temperature;
    }

    @Override
    public void setLighting(int lighting) {
        this.lighting = lighting;
    }

    @Override
    public void setRoomTheme(RoomTheme theme) {
        this.roomTheme = theme;
    }

    @Override
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}
