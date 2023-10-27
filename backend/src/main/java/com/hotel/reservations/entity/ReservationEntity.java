package com.hotel.reservations.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
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

    private int guestID;

    private Integer numGuests;

    private Date startDate;

    private Date endDate;

    private boolean isClaimed;

    private double stayPrice;

    private boolean isCancelled;

    private boolean isPaid;

    public ReservationEntity(RoomEntity room, RoomSettingEntity roomSetting, int guestID, Integer numGuests,
            Date startDate, Date endDate) {
        this.room = room;
        this.roomSetting = roomSetting;
        this.guestID = guestID;
        this.numGuests = numGuests;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isClaimed = false;
        this.isCancelled = false;
        this.isPaid = false;
        this.stayPrice = calculatePrice();
    }

    // Implement IChargeable soon
    private double calculatePrice() {

        // Room already has a price. We just need to calculate the number of days and
        // multiply by the price

        long timeDiff = Math.abs(endDate.getTime() - startDate.getTime());

        long daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);

        return daysDiff * room.getPrice();
    }

}
