package com.hotel.reservations.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "room_entity")
@Data
@NoArgsConstructor
public abstract class RoomEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private int roomId;

    private int roomNumber;

    private int capacity;
    
    private String roomType;
    
    private boolean isAvailable = true;

    private Double price;


    public abstract RoomEntity clone();
    
    public RoomEntity(RoomEntity room){
        this.roomId = room.roomId;
        this.roomNumber = room.roomNumber;
        this.capacity = room.capacity;
        this.roomType = room.roomType;
        this.isAvailable = room.isAvailable;
        this.price = room.price;
    }

    public void setPrice(Double price) {
        if(price > 0){
            this.price = price;
        }
    }
}
