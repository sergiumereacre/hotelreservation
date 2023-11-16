package com.hotel.payments.entity;

import com.hotel.payments.service.InvoiceFormat;

public class HTMLInvoiceFormat extends InvoiceFormat {

    @Override
    public String format() {
        return format(this.getInvoice());

    }

    @Override
    public String format(InvoiceEntity invoice) {

        try{
            double total = invoice.getTotal();
        }
        catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }


        return "<html><body><h1>Invoice</h1>" +
                "<p>Invoice ID: " + invoice.getInvoiceID() + "</p>" +
                "<p>Paid by: " + invoice.getGuest().getName() + "</p>" +
                "<p>Invoice Total: " + invoice.getTotal() + "</p></body></html>";

    }

    public HTMLInvoiceFormat(InvoiceEntity invoice) {
        super(invoice);
    }

    public HTMLInvoiceFormat() {
    }
}
