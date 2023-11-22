package com.hotel.reviews.entity;

import javax.persistence.Entity;

@Entity
// Concrete Command (Command Design Pattern)
public class WriteReviewCommand extends ReviewCommandEntity {
    private String resRef;
    private String reviewText;
    private int rating;

    public WriteReviewCommand(Long author, String resRef, String reviewText, int rating) {
        this.author = author;
        this.resRef = resRef;
        this.reviewText = reviewText;
        this.rating = rating;
    }

    @Override
    public boolean execute() {
        return false; //writeReview(userId, resRef, reviewText, rating);
    }
}

