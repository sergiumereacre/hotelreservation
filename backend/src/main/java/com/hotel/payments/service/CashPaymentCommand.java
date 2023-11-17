package com.hotel.payments.service;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.hotel.payments.entity.InvoiceEntity;

@Entity
public class CashPaymentCommand extends PaymentCommand {
    @OneToOne
    private InvoiceEntity invoice;

    public CashPaymentCommand(InvoiceEntity invoice) {
        this.invoice = invoice;
    }

    // Execute the command.
    @Override
    public void execute() {
        this.invoice.setPaymentType("Cash");
        this.invoice.setPaid(false);
    }
}
