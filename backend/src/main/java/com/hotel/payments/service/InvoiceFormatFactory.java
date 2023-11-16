package com.hotel.payments.service;

import org.springframework.stereotype.Component;

import com.hotel.payments.entity.HTMLInvoiceFormat;
import com.hotel.payments.entity.PlainTextInvoiceFormat;
import com.hotel.payments.interfaces.IFormatFactory;

@Component
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
