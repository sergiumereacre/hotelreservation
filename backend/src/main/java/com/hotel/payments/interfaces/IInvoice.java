package com.hotel.payments.interfaces;

import com.hotel.payments.entity.PaymentEntity;
import com.hotel.payments.service.InvoiceFormat;

public interface IInvoice {
    String printInvoice(InvoiceFormat format);
    boolean addBill(PaymentEntity bill);
    void removeBill(IBilling bill);
    void setRefund(IBilling bill, double refundAmount);
    double getTotal();
}
