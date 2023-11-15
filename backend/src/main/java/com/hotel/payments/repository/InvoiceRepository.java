package com.hotel.payments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.accounts.entity.AccountEntity;
import com.hotel.payments.entity.InvoiceEntity;
import java.util.List;



@Repository
public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long>{
    
    List<InvoiceEntity> findAllByGuestAndIsPaid(AccountEntity guest, boolean paid);
}
