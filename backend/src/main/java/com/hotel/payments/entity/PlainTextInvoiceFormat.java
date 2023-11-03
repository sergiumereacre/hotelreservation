package com.hotel.payments.entity;

import javax.persistence.Entity;

import com.hotel.payments.interfaces.IInvoice;
import com.hotel.payments.interfaces.IInvoiceFormat;

@Entity
public class PlainTextInvoiceFormat implements IInvoiceFormat {
    @Override
    public String formatInvoice(IInvoice invoice) {
        return "";
    }
}
