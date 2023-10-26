package com.hotel.reservations.interfaces;

import com.hotel.reservations.entity.ReservationEntity;

import java.util.Date;

public interface IReservationMgt {
    // Code for creating the reservation.
    ReservationEntity makeReservation(int guestId, int roomId, Date startDate, Date endDate, int numGuests);

    // Might be better to create a separate Confirmation class and return that instead
    String showConfirmation(ReservationEntity reservation);

    // Returning reservation reference from reservation.
    ReservationEntity getReservation(String reservationRef);

    // Might be better to throw an exception if the reservation cannot be cancelled
    void cancelReservation(String reservationRef);

    // Method for updating a reservation.
    ReservationEntity updateReservation(String reservationRef, int roomId, Date startDate, Date endDate, int numGuests);
}
