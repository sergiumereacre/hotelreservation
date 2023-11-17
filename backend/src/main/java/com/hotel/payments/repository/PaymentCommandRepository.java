package com.hotel.payments.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.payments.service.PaymentCommand;

public interface PaymentCommandRepository extends JpaRepository<PaymentCommand, Long>{
    
}
