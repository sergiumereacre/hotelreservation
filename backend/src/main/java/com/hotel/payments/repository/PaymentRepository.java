package com.hotel.payments.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.payments.entity.PaymentEntity;

public interface PaymentRepository extends JpaRepository<PaymentEntity, String> {

    
} 
