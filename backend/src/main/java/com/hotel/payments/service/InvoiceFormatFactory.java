package com.hotel.payments.service;

import com.hotel.payments.entity.HTMLInvoiceFormat;
import com.hotel.payments.entity.InvoiceEntity;
import com.hotel.payments.entity.PlainTextInvoiceFormat;
import com.hotel.payments.interfaces.IFormat;
import com.hotel.payments.interfaces.IFormatFactory;

public class InvoiceFormatFactory implements IFormatFactory {

    @Override
    public InvoiceFormat createFormat(String formatType) {
        switch (formatType) {
            case "HTML":
                return new HTMLInvoiceFormat();
            case "PLAINTEXT":
                return new PlainTextInvoiceFormat();
            default:
                return new PlainTextInvoiceFormat();
        }
    }
}
