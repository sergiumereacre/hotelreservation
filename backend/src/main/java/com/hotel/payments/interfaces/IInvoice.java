package com.hotel.payments.interfaces;

public interface IInvoice {
    String printInvoice(IInvoiceFormat format);
    boolean addBill(IChargeable bill);
    void removeBill(IBilling bill);
    void setRefund(IBilling bill, double refundAmount);
    double getTotal();
}
