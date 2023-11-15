package com.hotel.payments.interfaces;

public interface IFormatFactory {
    public IInvoiceFormat createFormat(String formatType);
}
