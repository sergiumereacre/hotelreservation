package com.hotel.reservations.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.hotel.reservations.interfaces.IRoomSetting;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.UUID;
import java.util.Date;

@Entity
@Data
public class ReservationEntity {
    @Id
    @Column(name = "reservation_ref", unique = true, nullable = false)
    private String reservationRef = UUID.randomUUID().toString();

    private RoomEntity room;
    private IRoomSetting roomSetting;

    @NotBlank(message = "GuestID cannot be blank")
    private String guestID;
    
    @NotNull(message= "Number of guests cannot be blank")
    @Size()
    private int numGuests;

    @NotNull(message = "Start date cannot be null")
    private Date startDate;

    @NotNull(message = "End date cannot be null")
    private Date endDate;

    private boolean isClaimed;

    @NotNull(message = "Stay price cannot be null")
    private double stayPrice;

    private boolean isCancelled;

    private boolean isPaid;

}
