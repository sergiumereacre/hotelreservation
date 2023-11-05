package com.hotel.reservations.entity;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SingleRoomEntity extends RoomEntity {
    public SingleRoomEntity(Double price) {
        this.setRoomType("Single");
        this.setCapacity(1);
        this.setPrice(price);
    }

    public SingleRoomEntity(SingleRoomEntity room) {
        super(room);
    }

    public SingleRoomEntity() {
    }

    @Override
    public SingleRoomEntity clone(){
        return new SingleRoomEntity(this);
    }
}
