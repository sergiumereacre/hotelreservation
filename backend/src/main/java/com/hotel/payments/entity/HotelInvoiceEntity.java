package com.hotel.payments.entity;

import java.util.List;

import javax.persistence.Entity;

import com.hotel.accounts.entity.AccountEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class HotelInvoiceEntity extends InvoiceEntity {
    public HotelInvoiceEntity(AccountEntity guest) {
        super(guest);
    }

    public HotelInvoiceEntity(AccountEntity guest, List<BillEntity> bills) {
        super(guest, bills);
    }
}
