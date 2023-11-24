package com.hotel.reviews.entity;

import javax.persistence.Entity;

// Concrete Command (Command Design Pattern)
@Entity
public class EditReviewTextCommand extends ReviewCommandEntity {
    private String reviewText;

    public EditReviewTextCommand(Long author, ReviewEntity review, String reviewText) {
        this.author = author;
        this.review = review;
        this.reviewText = reviewText;
    }

    @Override
    public void execute() {
        super.review.setReviewText(reviewText);
    }
}