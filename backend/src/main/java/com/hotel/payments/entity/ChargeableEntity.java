// package com.hotel.payments.entity;

// import javax.persistence.Column;
// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.Inheritance;
// import javax.persistence.InheritanceType;
// import javax.persistence.Table;

// import com.hotel.payments.interfaces.IChargeable;

// @Entity
// @Table(name = "chargeable_entity")
// @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
// public abstract class ChargeableEntity implements IChargeable{

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     @Column(name = "chargeable_id")
//     private Long chargeableId;

//     @Override
//     public double getPrice(){
//         return -1;
//     }

//     @Override
//     public String getDiscountDetails() {
//         return null;
//     }

//     @Override
//     public String getChargeDetails() {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'getChargeDetails'");
//     }

//     @Override
//     public void setIsPaid(boolean isPaid) {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'setIsPaid'");
//     }

//     @Override
//     public boolean getIsPaid() {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'getIsPaid'");
//     }
    
// }
