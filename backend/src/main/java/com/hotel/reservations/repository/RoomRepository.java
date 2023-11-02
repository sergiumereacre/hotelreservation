package com.hotel.reservations.repository;

import org.springframework.stereotype.Repository;

import com.hotel.reservations.entity.RoomEntity;

import java.util.Date;
import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Integer> {
    Optional<RoomEntity> findByRoomId(int roomId);

    // Get count of all rooms that are available between dates
    @Query("SELECT COUNT(*) FROM RoomEntity WHERE room_id NOT IN (SELECT room_id FROM ReservationEntity WHERE (start_date <= ?1 AND end_date >= ?1) OR (start_date <= ?2 AND end_date >= ?2))")
    public int getAvailableRoomsCount(Date startDate, Date endDate);

    @Query("SELECT r FROM rooms WHERE room_id NOT IN (SELECT room_id FROM ReservationEntity WHERE (start_date <= ?1 AND end_date >= ?1) OR (start_date <= ?2 AND end_date >= ?2))")
    public List<RoomEntity> getAvailableRooms(Date startDate, Date endDate);
}
