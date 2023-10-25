package com.hotel.reservations.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
@Data
public class RoomSettingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomSettingId;

    @NotBlank(message = "Room theme cannot be blank")
    private String roomTheme;

    @NotBlank(message = "Room temperature cannot be blank")
    private double temperature;

    @NotBlank(message = "Room lighting cannot be blank")
    private int lighting;
}
