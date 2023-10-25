package com.hotel.reservations.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotBlank;
import java.util.UUID;
import java.util.Date;

@Entity
public class Reservation {
    @Id
    private String reservationRef = UUID.randomUUID().toString();

    @NotBlank(message = "GuestID cannot be blank")
    private String guestID;
    
    @NotBlank(message= "Number of guests cannot be blank")
    @Size()
    private int numGuests;

    @NotNull(message = "Start date cannot be null")
    private Date startDate;

    @NotNull(message = "End date cannot be null")
    private Date endDate;

    private boolean isClaimed;

    @NotNull(message = "Stay price cannot be null")
    private double stayPrice;

    private boolean isCancelled;

    private boolean isPaid;
}
