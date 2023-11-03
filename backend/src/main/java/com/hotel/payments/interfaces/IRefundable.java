package com.hotel.payments.interfaces;

public interface IRefundable {
    double calculateRefund();

    void setRefundableAmount(double refundableAmount);
}
