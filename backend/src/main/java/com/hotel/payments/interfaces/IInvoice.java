package com.hotel.payments.interfaces;

public interface IInvoice {
    String printInvoice(IInvoiceFormat format);
    boolean addBill(IBilling bill);
    boolean removeBill(IBilling bill);
    boolean setRefund(IBilling bill, double refundAmount);
    double getTotal();
}
