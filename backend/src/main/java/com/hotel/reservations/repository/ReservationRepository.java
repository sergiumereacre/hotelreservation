package com.hotel.reservations.repository;

import com.hotel.reservations.entity.ReservationEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, String> {
    Optional<ReservationEntity> findByReservationRef(String reservationRef);

    // // Find by list of room ids, start date, and end date
    Optional<ReservationEntity> findByRoomInAndStartDateLessThanEqualAndEndDateGreaterThanEqual(List<Integer> roomIds, LocalDate startDate, LocalDate endDate);

     // Find by list of room ids, start date, and end date
    // Optional<ReservationEntity> findByStartDateLessThanEqualAndEndDateGreaterThanEqual(LocalDate startDate, LocalDate endDate);
}
