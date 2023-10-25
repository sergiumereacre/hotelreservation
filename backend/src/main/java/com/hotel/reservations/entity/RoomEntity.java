package com.hotel.reservations.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Data
public class RoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomId;

    @NotBlank(message = "Room number cannot be blank")
    private int roomNumber;

    @NotBlank(message = "Room type cannot be blank")
    private String roomType;

    @NotBlank(message = "Room capacity cannot be blank")
    private boolean isAvailable;

    @NotBlank(message = "Room price cannot be blank")
    private double price;

}
