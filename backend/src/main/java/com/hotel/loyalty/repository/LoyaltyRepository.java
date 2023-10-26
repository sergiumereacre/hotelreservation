package com.hotel.loyalty.repository;

import com.hotel.accounts.entity.GuestAccountEntity;
import com.hotel.loyalty.entity.LoyaltyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoyaltyRepository extends JpaRepository<LoyaltyEntity, Long> {
    LoyaltyEntity findByAccount(GuestAccountEntity account);
}

