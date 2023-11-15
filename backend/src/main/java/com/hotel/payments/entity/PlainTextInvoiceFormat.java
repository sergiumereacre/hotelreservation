package com.hotel.payments.entity;

import com.hotel.payments.interfaces.IInvoice;

import com.hotel.payments.service.InvoiceFormat;

public class PlainTextInvoiceFormat extends InvoiceFormat {
    @Override
    public String format() {
        return "Invoice\n" +
        "Invoice ID: " + getInvoice().getInvoiceID() + "\n" +
        "Paid by: " + getInvoice().getGuest().getName() + "\n" +
        "Invoice Total: " + getInvoice().getTotal() + "\n";
    }

    public PlainTextInvoiceFormat(InvoiceEntity invoice) {
        super(invoice);
    }

    public PlainTextInvoiceFormat() {
    }
}
