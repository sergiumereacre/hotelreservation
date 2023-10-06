package com.hotel.accounts.repository;

import com.hotel.accounts.entity.GuestAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestAccountRepository extends JpaRepository<GuestAccountEntity, Long> {
    // add query method if we need to customise them
}

