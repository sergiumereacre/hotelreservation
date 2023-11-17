package com.hotel.reviews.entity;

import javax.persistence.Entity;

import com.hotel.reviews.interfaces.ReviewCommand;
import com.hotel.reviews.service.ReviewService;

@Entity
// Concrete Command (Command Design Pattern)
public class WriteReviewCommand implements ReviewCommand {
    private ReviewService reviewMgt;
    private int userId;
    private String resRef;
    private String reviewText;
    private int rating;

    public WriteReviewCommand(ReviewService reviewMgt, int userId, String resRef, String reviewText, int rating) {
        this.reviewMgt = reviewMgt;
        this.userId = userId;
        this.resRef = resRef;
        this.reviewText = reviewText;
        this.rating = rating;
    }

    @Override
    public boolean execute() {
        return reviewMgt.writeReview(userId, resRef, reviewText, rating);
    }
}

