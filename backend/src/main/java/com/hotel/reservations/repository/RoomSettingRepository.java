package com.hotel.reservations.repository;

import org.springframework.stereotype.Repository;
import com.hotel.reservations.entity.RoomSettingEntity;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface RoomSettingRepository extends JpaRepository<RoomSettingEntity, Long> {
    
}
