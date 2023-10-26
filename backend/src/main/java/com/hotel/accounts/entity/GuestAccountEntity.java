package com.hotel.accounts.entity;

import lombok.Data;
import com.hotel.loyalty.interfaces.ILoyaltyObserver;
import com.hotel.loyalty.service.Loyalty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Data
public class GuestAccountEntity implements ILoyaltyObserver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    private String password;

    private int numStays;

    @Override
    public void updateLoyalty(GuestAccountEntity guestAccount) {
        // Implement custom logic to update the loyalty status based on numOfStays
        int numStays = guestAccount.getNumStays();
        Loyalty loyaltyStatus;

        if (numStays >= 10) {
            loyaltyStatus = Loyalty.GOLD;
        } else if (numStays >= 5) {
            loyaltyStatus = Loyalty.SILVER;
        } else {
            loyaltyStatus = Loyalty.BRONZE;
        }
    }
}

