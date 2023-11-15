package com.hotel.payments.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.hotel.accounts.entity.AccountEntity;
import com.hotel.payments.interfaces.IBilling;
import com.hotel.payments.interfaces.IInvoice;
import com.hotel.payments.interfaces.IInvoiceFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@Data
public abstract class InvoiceEntity implements IInvoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long invoiceID;

    @OneToMany
    private List<BillEntity> bills;

    @OneToOne
    @JoinColumn(name = "guest_id")
    private AccountEntity guest;

    private boolean isPaid;

    private String paymentType;

    public InvoiceEntity(AccountEntity guest) {
        this.guest = guest;
    }

    public InvoiceEntity(AccountEntity guest, List<BillEntity> bills) {
        this.bills = bills;
        this.guest = guest;
    }

     @Override
    public String printInvoice(IInvoiceFormat format) {
        return "";
    }

    @Override
    public boolean addBill(PaymentEntity bill) {
        this.bills.add(new BillEntity(bill, false));

        return true;
    }

    @Override
    public void removeBill(IBilling bill) {
        this.bills.remove(bill);
    }

    @Override
    public void setRefund(IBilling bill, double refundAmount) {
        for (BillEntity billEntity : this.bills) {
            if (billEntity.getPayment().equals(bill)) {
                billEntity.setRefundableAmount(refundAmount);
                billEntity.setRefundable(true);
            }
        }
    }

    @Override
    public double getTotal() {
        double total = 0;

        for (BillEntity billEntity : this.bills) {
            total += billEntity.calculateBill();
        }

        return total;
    }

    public InvoiceEntity(List<BillEntity> bills) {
        this.bills = bills;
    }
}
