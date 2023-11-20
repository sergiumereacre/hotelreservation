package com.hotel.reviews.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.hotel.reviews.interfaces.ReviewCommand;

import lombok.Data;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public abstract class ReviewCommandEntity implements ReviewCommand {
    @Id
    double reviewCommandId;


}
