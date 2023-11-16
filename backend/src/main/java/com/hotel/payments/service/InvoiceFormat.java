package com.hotel.payments.service;

import com.hotel.payments.entity.InvoiceEntity;
import com.hotel.payments.interfaces.IFormat;

public abstract class InvoiceFormat implements IFormat {

    private InvoiceEntity invoice;

    public InvoiceFormat(InvoiceEntity invoice) {
        this.invoice = invoice;
    }

    public InvoiceFormat() {
    }

    public void setInvoice(InvoiceEntity invoice) {
        this.invoice = invoice;
    }

    public InvoiceEntity getInvoice() {
        return invoice;
    }

    public abstract String format(InvoiceEntity invoice);
}
