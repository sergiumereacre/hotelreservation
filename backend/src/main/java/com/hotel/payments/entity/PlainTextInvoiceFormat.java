package com.hotel.payments.entity;

import com.hotel.payments.service.InvoiceFormat;

public class PlainTextInvoiceFormat extends InvoiceFormat {
    @Override
    public String format() {
        return format(this.getInvoice());
    }

    @Override
    public String format(InvoiceEntity invoice) {
        return "Invoice\n" +
        "Invoice ID: " + invoice.getInvoiceID() + "\n" +
        "Paid by: " + invoice.getGuest().getName() + "\n" +
        "Invoice Total: " + invoice.getTotal() + "\n";
    }

    public PlainTextInvoiceFormat(InvoiceEntity invoice) {
        super(invoice);
    }

    public PlainTextInvoiceFormat() {
    }
}
