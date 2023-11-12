package com.hotel.reservations.service;

import java.util.Calendar;

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

    // Can possibly add Command pattern here
    // We can do this by creating a CheckInCommand and CheckOutCommand
    // and passing the reservation to the constructor
    // and then calling the execute method
    // This will allow us to undo the check in and check out
    // by calling the undo method
    // This will also allow us to add more commands in the future
    // without having to change the code here
    public boolean doCheckIn(String reservationRef) {
        ReservationEntity reservation = reservationService.getReservation(reservationRef);

        if (reservation == null) {
            return false;
        }

        // applyEarlyCheckInFee(reservation);
        applyEngagementFee(reservation, EngageFeeType.EARLY_CHECKIN);

        return setClaimedStatus(reservation, true);
    }

    public boolean doCheckOut(String reservationRef) {
        ReservationEntity reservation = reservationService.getReservation(reservationRef);

        if (reservation == null) {
            return false;
        }

        // applyLateCheckOutFee(reservation);
        applyEngagementFee(reservation, EngageFeeType.LATE_CHECKOUT);

        return setClaimedStatus(reservation, false);
    }

    private boolean setClaimedStatus(ReservationEntity res, boolean claimed) {
        if (res != null) {
            res.setClaimed(claimed);
            reservationRepository.save(res);
            return true;
        }
        return false;
    }

    private void applyEngagementFee(ReservationEntity reservation, EngageFeeType feeType) {
        Calendar checkInTime = Calendar.getInstance();
        int hour = checkInTime.get(Calendar.HOUR_OF_DAY);

        int difference = CHECK_IN_HOUR - hour;

        if (difference > 0) {
            switch (feeType) {
                case EARLY_CHECKIN:
                    reservation.setStayPrice(reservation.getPrice() + difference * EARLY_CHECKIN_HOURLY_RATE);
                    break;

                case LATE_CHECKOUT:
                    reservation.setStayPrice(reservation.getPrice() + difference * LATE_CHECKOUT_HOURLY_RATE);
                    break;

                default:
                    break;
            }
        }
    }

    // private void applyEarlyCheckInFee(ReservationEntity reservation) {

    // if (difference > 0) {
    // reservation.setStayPrice(reservation.getPrice() + difference *
    // EARLY_CHECKIN_HOURLY_RATE);
    // }
    // }

    // private void applyLateCheckOutFee(ReservationEntity reservation) {
    // Calendar checkInTime = Calendar.getInstance();
    // int hour = checkInTime.get(Calendar.HOUR_OF_DAY);

    // int difference = hour - CHECK_OUT_HOUR;

    // if (difference > 0) {
    // reservation.setStayPrice(reservation.getPrice() + difference *
    // LATE_CHECKOUT_HOURLY_RATE);
    // }
    // }
}

enum EngageFeeType {
    EARLY_CHECKIN, LATE_CHECKOUT
}