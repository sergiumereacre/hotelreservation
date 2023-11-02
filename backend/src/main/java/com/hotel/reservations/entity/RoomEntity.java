package com.hotel.reservations.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
public class RoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomId;

    private int roomNumber;

    private int capacity;
    
    private String roomType;
    
    private boolean isAvailable;

    private Double price;

    // public boolean getIsAvailable(){
    //     return isAvailable;
    // }

}
