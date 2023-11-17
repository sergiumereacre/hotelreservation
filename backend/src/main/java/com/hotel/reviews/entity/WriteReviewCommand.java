package com.hotel.reviews.entity;

import javax.persistence.Entity;

import com.hotel.reviews.interfaces.ReviewCommand;
import com.hotel.reviews.service.ReviewService;

@Entity
// Concrete Command (Command Design Pattern)
public class WriteReviewCommand implements ReviewCommand {
    private ReviewService reviewService;

    private int userId;
    private String resRef;
    private String reviewText;
    private int rating;

    public WriteReviewCommand(ReviewService reviewService, int userId, String resRef, String reviewText, int rating) {
        this.reviewService = reviewService;
        this.userId = userId;
        this.resRef = resRef;
        this.reviewText = reviewText;
        this.rating = rating;
    }

    @Override
    public boolean execute() {
        return reviewService.writeReview(userId, resRef, reviewText, rating);
    }
}

