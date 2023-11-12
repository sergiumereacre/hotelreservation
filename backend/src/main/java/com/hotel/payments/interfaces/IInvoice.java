package com.hotel.payments.interfaces;

import com.hotel.payments.entity.PaymentEntity;

public interface IInvoice {
    String printInvoice(IInvoiceFormat format);
    boolean addBill(PaymentEntity bill);
    void removeBill(IBilling bill);
    void setRefund(IBilling bill, double refundAmount);
    double getTotal();
}
