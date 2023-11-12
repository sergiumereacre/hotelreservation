package com.hotel.reservations.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.hotel.payments.entity.PaymentEntity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "reservations")
@AllArgsConstructor
@Data
public class ReservationEntity extends PaymentEntity {
    
    // Might create Idclass later
    private String reservationRef = UUID.randomUUID().toString();

// (fetch = FetchType.LAZY)

    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "room_id")
    private RoomEntity room;

    @OneToOne
    private RoomSettingEntity roomSetting;

    private int guestID;

    private Integer numGuests;

    private LocalDate startDate;

    private LocalDate endDate;

    private boolean isClaimed;

    private double stayPrice;

    private boolean isCancelled;

    private boolean isPaid;

    public ReservationEntity() {
    }

    public ReservationEntity(String paymentRef) {
        super(paymentRef);
    }

    public ReservationEntity(RoomEntity room, RoomSettingEntity roomSetting, int guestID, Integer numGuests,
            LocalDate startDate, LocalDate endDate) {
        this.room = room;
        this.roomSetting = roomSetting;
        this.guestID = guestID;
        this.numGuests = numGuests;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isClaimed = false;
        this.isCancelled = false;
        this.isPaid = false;
        this.stayPrice = getPrice();
    }

    // Implement IChargeable soon
    @Override
    public double getPrice() {

        // Room already has a price. We just need to calculate the number of days and
        // multiply by the price

        long daysDiff = ChronoUnit.DAYS.between(startDate, endDate);

        return daysDiff * room.getPrice();
    }

    @Override
    public void setIsPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    @Override
    public boolean getIsPaid() {
        return this.isPaid;
    }

    @Override
    public String getDiscountDetails() {
        return "";
    }

    @Override
    public String getChargeDetails() {
        return "Reservation for " + numGuests + " guests from " + startDate + " to " + endDate + " at room "
                + room.getRoomId() + " for " + getPrice() + "";
    }

    public void setStartDate(String startDate) {
        this.startDate = LocalDate.parse(startDate);
    }

    public void setEndDate(String endDate) {
        this.endDate = LocalDate.parse(endDate);
    }

}
