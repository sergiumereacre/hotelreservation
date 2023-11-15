package com.hotel.payments.entity;

import com.hotel.payments.interfaces.IInvoice;
import com.hotel.payments.interfaces.IInvoiceFormat;

public class PlainTextInvoiceFormat implements IInvoiceFormat {
    @Override
    public String formatInvoice(IInvoice invoice) {
        return "";
    }
}
