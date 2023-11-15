package com.hotel.payments.entity;

import com.hotel.payments.service.InvoiceFormat;

public class HTMLInvoiceFormat extends InvoiceFormat {

    @Override
    public String format() {
        return "<html><body><h1>Invoice</h1>" +
        "<p>Invoice ID: " + getInvoice().getInvoiceID() + "</p>" +
        "<p>Paid by: " + getInvoice().getGuest().getName() + "</p>" +
        "<p>Invoice Total: " + getInvoice().getTotal() + "</p></body></html>";

    }

    public HTMLInvoiceFormat(InvoiceEntity invoice) {
        super(invoice);
    }

    public HTMLInvoiceFormat() {
    }
}
