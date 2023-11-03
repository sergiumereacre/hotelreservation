package com.hotel.payments.entity;

import javax.persistence.Entity;

import com.hotel.payments.interfaces.IBilling;
import com.hotel.payments.interfaces.IChargeable;
import com.hotel.payments.interfaces.IRefundable;

import lombok.Data;

@Entity
@Data
public class BillEntity implements IRefundable, IBilling {

    private IChargeable payment;
    private boolean isRefundable;
    private double refundableAmount;
    private final double CITY_TAX = 3;
    private final double TOURIST_TAX = 5;

    public BillEntity(IChargeable payment, boolean isRefundable) {
        this.payment = payment;
        this.isRefundable = isRefundable;
    }
    
    @Override
    public double calculateBill() {

        double bill = this.payment.getPrice();

        bill += CITY_TAX;
        bill += TOURIST_TAX;

        if (this.isRefundable) {
            bill -= this.refundableAmount;
        }

        return bill;
    }

    @Override
    public boolean isRefundable() {
        return this.isRefundable;
    }

    @Override
    public double calculateRefund() {
        return this.refundableAmount;
    }

    @Override
    public void setRefundableAmount(double refundableAmount) {
        this.refundableAmount = refundableAmount;
    }

    String getDiscountDetails(){
        return payment.getDiscountDetails();
    }


}
