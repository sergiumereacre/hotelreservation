package com.hotel.reviews.entity;

import javax.persistence.Entity;

@Entity
// Concrete Command (Command Design Pattern)
public class EditReviewCommand extends ReviewCommandEntity {
    private String reviewText;
    private int rating;

    public EditReviewCommand(Long author, Long reviewId, String reviewText, int rating) {
        this.author = author;
        this.reviewId = reviewId;
        this.reviewText = reviewText;
        this.rating = rating;
    }

    @Override
    public boolean execute() {
        return false; //reviewService.editReview(userId, reviewId, reviewText, rating);
    }
}

