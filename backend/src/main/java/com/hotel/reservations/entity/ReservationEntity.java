package com.hotel.reservations.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;


import java.util.UUID;
import java.util.Date;

@Entity
@Data
@Table(name = "reservations")
public class ReservationEntity {
    @Id
    @Column(name = "reservation_ref", unique = true, nullable = false)
    private String reservationRef = UUID.randomUUID().toString();

    @OneToOne
    @JoinColumn(name = "room_id")
    private RoomEntity room;

    @OneToOne
    private RoomSettingEntity roomSetting;

    private String guestID;

    private Integer numGuests;

    private Date startDate;

    private Date endDate;

    private boolean isClaimed;

    private double stayPrice;

    private boolean isCancelled;

    private boolean isPaid;

}
