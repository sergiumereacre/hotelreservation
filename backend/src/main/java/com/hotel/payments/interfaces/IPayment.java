package com.hotel.payments.interfaces;

public interface IPayment {
 boolean processPayment(Long invoiceId, String paymentType);

//  IInvoice generateIInvoice(List<IBilling> billings);
    
}