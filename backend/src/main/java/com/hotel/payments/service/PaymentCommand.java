package com.hotel.payments.service;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.hotel.payments.interfaces.IPaymentCommand;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@NoArgsConstructor
public abstract class PaymentCommand implements IPaymentCommand{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long paymentCommandId;

}
