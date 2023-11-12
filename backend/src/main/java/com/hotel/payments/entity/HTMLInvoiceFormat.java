package com.hotel.payments.entity;

import javax.persistence.Entity;

import com.hotel.payments.interfaces.IInvoice;
import com.hotel.payments.interfaces.IInvoiceFormat;

// @Entity
public class HTMLInvoiceFormat implements IInvoiceFormat {

    @Override
    public String formatInvoice(IInvoice invoice) {
        return "";
    }
}
