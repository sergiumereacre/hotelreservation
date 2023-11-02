package com.hotel.reservations.repository;

import com.hotel.reservations.entity.ReservationEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, String> {
    Optional<ReservationEntity> findByReservationRef(String reservationRef);
}
