package com.hotel.payments.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.hotel.payments.interfaces.IBilling;
import com.hotel.payments.interfaces.IRefundable;

import lombok.Data;

@Entity
@Data
public class BillEntity implements IRefundable, IBilling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long billId;

    @OneToOne
    private PaymentEntity payment;
    
    private boolean isRefundable;
    private double refundableAmount;
    private final double CITY_TAX = 3;
    private final double TOURIST_TAX = 5;

    public BillEntity(PaymentEntity payment, boolean isRefundable) {
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

    public BillEntity(PaymentEntity payment) {
        this.payment = payment;
    }

}
