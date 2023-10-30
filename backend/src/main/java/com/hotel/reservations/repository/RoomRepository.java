package com.hotel.reservations.repository;

import org.springframework.stereotype.Repository;

import com.hotel.reservations.entity.RoomEntity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Long> {
    Optional<RoomEntity> findByRoomId(int roomId);
}
