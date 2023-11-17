package com.hotel.reviews.entity;

import javax.persistence.Entity;

import com.hotel.reviews.interfaces.ReviewCommand;
import com.hotel.reviews.service.ReviewService;

@Entity
// Concrete Command (Command Design Pattern)
public class EditReviewCommand implements ReviewCommand {
    private ReviewService reviewMgt;
    private int userId;
    private int reviewId;
    private String reviewText;
    private int rating;

    public EditReviewCommand(ReviewService reviewMgt, int userId, int reviewId, String reviewText, int rating) {
        this.reviewMgt = reviewMgt;
        this.userId = userId;
        this.reviewId = reviewId;
        this.reviewText = reviewText;
        this.rating = rating;
    }

    @Override
    public boolean execute() {
        return reviewMgt.editReview(userId, reviewId, reviewText, rating);
    }
}

