package com.hotel.payments.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.payments.entity.CardEntity;

public interface CardRepository extends JpaRepository<CardEntity, Integer> {
    
}
