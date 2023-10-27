package com.hotel.reservations.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hotel.reservations.entity.ReservationEntity;
import com.hotel.reservations.interfaces.IReservationMgt;
import com.hotel.reservations.repository.ReservationRepository;
import com.hotel.reservations.entity.ReservationEntity;

import java.util.Date;
import java.util.List;

@Service
public class ReservationService implements IReservationMgt {

    @Autowired
    private ReservationRepository repository;

    public ReservationEntity saveReservation(ReservationEntity reservation) {
        return repository.save(reservation);
    }

    @Override
        public List<ReservationEntity> getAllReservations() {
        return repository.findAll();
    }

    public ReservationEntity getGuestById(String reservationRef) {
        return repository.findById(reservationRef).orElse(null);
    }

    public void deleteReservation(String reservationRef) {
        repository.deleteById(reservationRef);
    }

    @Override
    public boolean cancelReservation(String reservationRef) {
        // TODO Auto-generated method stub
        return false;
        
    }

    @Override
    public ReservationEntity getReservation(String reservationRef) {
        // TODO Auto-generated method stub
        return null;
    }



    @Override
    public ReservationEntity makeReservation(int guestId, int roomId, Date startDate, Date endDate, int numGuests) {
        // TODO Auto-generated method stub
        return null;
    }

    // @Override
    // public String showConfirmation(ReservationEntity reservation) {
    //     // TODO Auto-generated method stub
    //     return null;
    // }

    @Override
    public ReservationEntity updateReservation(String reservationRef, int roomId, Date startDate, Date endDate,
            int numGuests) {
        // TODO Auto-generated method stub
        return null;
    }
}
