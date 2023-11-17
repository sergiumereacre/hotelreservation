package com.hotel.payments.interfaces;

public interface IChargeable {
    double getPrice();
    String getDiscountDetails();
    String getChargeDetails();
}
