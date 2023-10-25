package com.hotel.reservations.interfaces;

import com.hotel.reservations.entity.Reservation;
import java.util.Date;

public interface IReservationMgt {
    Reservation makeReservation(int guestId, int roomId, Date startDate, Date endDate, int numGuests);

    // Might be better to create a separate Confirmation class and return that instead
    String showConfirmation(Reservation reservation);

    Reservation getReservation(String reservationRef);

    // Might be better to throw an exception if the reservation cannot be cancelled
    void cancelReservation(String reservationRef);

    Reservation updateReservation(String reservationRef, int roomId, Date startDate, Date endDate, int numGuests);
}
