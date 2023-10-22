package com.hotel.accounts.repository;

import com.hotel.accounts.entity.GuestAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GuestAccountRepository extends JpaRepository<GuestAccountEntity, Long> {
    Optional<GuestAccountEntity> findByEmail(String email);
}

