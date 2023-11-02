package com.hotel.reservations.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.reservations.entity.ReservationEntity;
import com.hotel.reservations.interfaces.IEngageReservation;
import com.hotel.reservations.repository.ReservationRepository;

@Service
public class EngageReservationService implements IEngageReservation {
    // Check in time is 12pm
    final private int CHECK_IN_HOUR = 12;

    final private double EARLY_CHECKIN_HOURLY_RATE = 20;

    // Check out time is 11am
    final private int CHECK_OUT_HOUR = 11;

    final private int LATE_CHECKOUT_HOURLY_RATE = 20;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationService reservationService;

    public boolean doCheckIn(String reservationRef) {
        ReservationEntity reservation = reservationService.getReservation(reservationRef);

        if (reservation == null) {
            return false;
        }

        applyEarlyCheckInFee(reservation);

        return setClaimedStatus(reservation, true);
    }

    public boolean doCheckOut(String reservationRef) {
        ReservationEntity reservation = reservationService.getReservation(reservationRef);

        if (reservation == null) {
            return false;
        }

        applyLateCheckOutFee(reservation);

        return setClaimedStatus(reservation, false);
    }

    // Function that Toggle the claimed status of the reservation
    private boolean setClaimedStatus(ReservationEntity res, boolean claimed) {
        if (res != null) {
            res.setClaimed(claimed);
            reservationRepository.save(res);
            return true;
        }
        return false;
    }

    private void applyEarlyCheckInFee(ReservationEntity reservation) {
        Calendar checkInTime = Calendar.getInstance();
        int hour = checkInTime.get(Calendar.HOUR_OF_DAY);

        int difference = CHECK_IN_HOUR - hour;

        if (difference > 0) {
            reservation.setStayPrice(reservation.getStayPrice() + difference * EARLY_CHECKIN_HOURLY_RATE);
        }
    }

    private void applyLateCheckOutFee(ReservationEntity reservation) {
        Calendar checkInTime = Calendar.getInstance();
        int hour = checkInTime.get(Calendar.HOUR_OF_DAY);

        int difference = hour - CHECK_OUT_HOUR;

        if (difference > 0) {
            reservation.setStayPrice(reservation.getStayPrice() + difference * LATE_CHECKOUT_HOURLY_RATE);
        }
    }
}
