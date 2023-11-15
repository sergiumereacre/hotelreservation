package com.hotel.payments.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.hotel.accounts.entity.AccountEntity;
import com.hotel.payments.interfaces.IInvoice;

import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
public abstract class InvoiceEntity implements IInvoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long invoiceID;

    @OneToOne
    @JoinColumn(name = "guest_id")
    private AccountEntity guest;

    public InvoiceEntity(AccountEntity guest) {
        this.guest = guest;
    }

    public void setGuest(AccountEntity guest) {
        this.guest = guest;
    }
}
