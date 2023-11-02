package com.hotel.payments.interfaces;

import java.util.List;

public interface IPayment {
 boolean processPayment(IInvoice invoice, String paymentType);

 IInvoice generateIInvoice(List<IBilling> billings);
    
}