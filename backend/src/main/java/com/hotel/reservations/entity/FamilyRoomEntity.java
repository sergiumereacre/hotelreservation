package com.hotel.reservations.entity;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class FamilyRoomEntity extends RoomEntity {
    public FamilyRoomEntity(Double price) {
        this.setRoomType("Family");
        this.setCapacity(4);
        this.setPrice(price);
    }

    public FamilyRoomEntity(FamilyRoomEntity room) {
        super(room);
    }

    public FamilyRoomEntity() {
    }

    @Override
    public FamilyRoomEntity clone(){
        return new FamilyRoomEntity(this);
    }
}
