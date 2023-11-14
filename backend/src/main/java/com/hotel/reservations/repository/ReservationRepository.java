package com.hotel.reservations.repository;

import com.hotel.reservations.entity.ReservationEntity;
import com.hotel.reservations.entity.RoomEntity;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, String> {
        Optional<ReservationEntity> findByReservationRef(String reservationRef);

        // // Find by list of room ids, start date, and end date
        // @Query("SELECT r FROM ReservationEntity r WHERE r.room IN :roomIds")
        @Query("SELECT r FROM ReservationEntity r WHERE r.room = :roomId AND  r.startDate <= :endDate AND r.endDate >= :startDate")
        Optional<ReservationEntity> findByRoomsInDateRange(@Param("roomId") Integer roomId,
                        @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

        Optional<ReservationEntity> findByRoomAndStartDateLessThanEqualAndEndDateGreaterThanEqual(RoomEntity room,
                        LocalDate startDate, LocalDate endDate);

        Optional<ReservationEntity> findByRoomAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndIsCancelledFalse(
                        RoomEntity room,
                        LocalDate startDate, LocalDate endDate);

        // Also checks if the reservation is cancelled.

        Optional<ReservationEntity> findByRoom(RoomEntity room);

        // Find by list of room ids, start date, and end date
        // Optional<ReservationEntity>
        // findByStartDateLessThanEqualAndEndDateGreaterThanEqual(LocalDate startDate,
        // LocalDate endDate);
}
