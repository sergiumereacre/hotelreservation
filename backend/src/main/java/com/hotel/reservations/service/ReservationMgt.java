package com.hotel.reservations.service;

import org.springframework.stereotype.Service;
import com.hotel.reservations.entity.ReservationEntity;
import com.hotel.reservations.repository.ReservationRepository;
import java.util.Date;
import java.util.List;

@Service
public class ReservationMgt {

    @Autowired
    private ReservationRepository repository;

    public ReservationEntity saveReservation(ReservationEntity reservation) {
        return repository.save(reservation);
    }

    public List<ReservationEntity> getAllReservation() {
        return repository.findAll();
    }

    public ReservationEntity getGuestById(String reservationRef) {
        return repository.findById(reservationRef).orElse(null);
    }

    public void deleteReservation(String reservationRef) {
        repository.deleteById(reservationRef);
    }
}
