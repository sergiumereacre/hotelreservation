package com.hotel.payments.entity;

import java.util.UUID;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

import com.hotel.payments.interfaces.IChargeable;

import lombok.Data;

@Entity
@Table(name = "payments")
@Inheritance(strategy = InheritanceType.JOINED)
// @MappedSuperclass
public abstract class PaymentEntity implements IChargeable{

    @Id
    @Column(name = "payment_ref", unique = true, nullable = false)
    private String paymentRef = UUID.randomUUID().toString();

    public abstract double getPrice();
    public abstract String getDiscountDetails();
    public abstract String getChargeDetails();
    public abstract void setIsPaid(boolean isPaid);
    public abstract boolean getIsPaid();

    public PaymentEntity() {
    }
}
