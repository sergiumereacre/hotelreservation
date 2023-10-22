package com.hotel.accounts.repository;
import com.hotel.accounts.entity.HotelStaffAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelStaffAccountRepository extends JpaRepository<HotelStaffAccountEntity, Long> {
    // add methods if needed
}