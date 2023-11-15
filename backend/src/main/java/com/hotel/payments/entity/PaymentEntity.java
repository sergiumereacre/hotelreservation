package com.hotel.payments.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.hotel.payments.interfaces.IChargeable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payments")
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@Data
// @MappedSuperclass
public abstract class PaymentEntity implements IChargeable{

    @Id
    @Column(name = "payment_ref", unique = true, nullable = false)
    private String paymentRef = UUID.randomUUID().toString();
    
    public PaymentEntity(String paymentRef) {
        this.paymentRef = paymentRef;
    }
}
