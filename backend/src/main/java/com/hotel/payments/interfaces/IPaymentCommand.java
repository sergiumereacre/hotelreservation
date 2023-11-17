package com.hotel.payments.interfaces;

import com.hotel.payments.entity.PaymentEntity;

public interface IPaymentCommand {
    PaymentEntity execute();
}
