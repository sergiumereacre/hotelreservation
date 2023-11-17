package com.hotel.payments.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.payments.entity.BillEntity;

public interface BillRepository extends JpaRepository<BillEntity, Long> {
     
}
