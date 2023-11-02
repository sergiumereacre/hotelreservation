package com.hotel.loyalty.repository;

import com.hotel.loyalty.entity.LoyaltyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoyaltyRepository extends JpaRepository<LoyaltyEntity, Long> {

    Optional<LoyaltyEntity> findById(Long id);

}

