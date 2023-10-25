package com.hotel.accounts.repository;
import com.hotel.accounts.entity.HotelStaffAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HotelStaffAccountRepository extends JpaRepository<HotelStaffAccountEntity, Long> {
    // add methods if needed
    Optional<HotelStaffAccountEntity> findByEmailAndPassword(String email, String password);
    Optional<HotelStaffAccountEntity> findByEmail(String email);
}