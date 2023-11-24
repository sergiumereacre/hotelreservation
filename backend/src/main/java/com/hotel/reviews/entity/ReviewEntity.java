package com.hotel.reviews.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.hotel.accounts.entity.AccountEntity;
import com.hotel.reservations.entity.ReservationEntity;

import lombok.Data;

// Reciever (Command Design Pattern)
@Entity
@Data
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;
    
    private String reviewText;
    private int rating;
    private LocalDateTime timeCreated;
    private LocalDateTime lastUpdated;

    @ManyToOne
    private AccountEntity author;

    @OneToOne
    private ReservationEntity reservation;
}