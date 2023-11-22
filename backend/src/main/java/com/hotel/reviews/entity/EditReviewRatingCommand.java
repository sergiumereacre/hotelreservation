package com.hotel.reviews.entity;

import javax.persistence.Entity;

@Entity
// Concrete Command (Command Design Pattern)
public class EditReviewRatingCommand extends ReviewCommandEntity {
    private int rating;

    public EditReviewRatingCommand(Long author, ReviewEntity review, int rating) {
        this.author = author;
        this.review = review;
        this.rating = rating;
    }

    @Override
    public void execute() {
        super.review.setRating(rating);
    }
}