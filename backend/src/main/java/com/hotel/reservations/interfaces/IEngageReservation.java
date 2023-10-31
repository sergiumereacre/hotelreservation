package com.hotel.reservations.interfaces;

public interface IEngageReservation {
    boolean doCheckIn(String reservationRef);

    boolean doCheckOut(String reservationRef);
}
