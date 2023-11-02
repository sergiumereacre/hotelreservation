package com.hotel.payments.interfaces;

import java.util.List;

public interface IInvoiceFormat {
    String formatInvoice(List<IBilling> billings);
}
