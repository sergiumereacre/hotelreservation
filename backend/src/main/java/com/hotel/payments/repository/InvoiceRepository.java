package com.hotel.payments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hotel.payments.entity.InvoiceEntity;


@Repository
public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long>{
    
}
