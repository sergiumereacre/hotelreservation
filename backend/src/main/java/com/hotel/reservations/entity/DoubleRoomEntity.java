package com.hotel.reservations.entity;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class DoubleRoomEntity extends RoomEntity {
    public DoubleRoomEntity(Double price) {
        this.setRoomType("Double");
        this.setCapacity(2);
        this.setPrice(price);
    }

    public DoubleRoomEntity(DoubleRoomEntity room) {
        super(room);
    }

    @Override
    public DoubleRoomEntity clone(){
        return new DoubleRoomEntity(this);
    }
}
