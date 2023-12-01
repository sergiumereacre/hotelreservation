package com.hotel.reviews.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;

import com.hotel.reviews.interfaces.IReviewCommand;

import lombok.Data;

// Command (Command Design Pattern)
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public abstract class ReviewCommandEntity implements IReviewCommand {
    @Id
    double reviewCommandId;
    
    @OneToOne
    protected ReviewEntity review;

    protected Long author;
}
