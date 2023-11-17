package com.hotel.reviews.entity;

import javax.persistence.Entity;

import com.hotel.reviews.interfaces.ReviewCommand;
import com.hotel.reviews.service.ReviewService;

@Entity
// Concrete Command (Command Design Pattern)
public class DeleteReviewCommand implements ReviewCommand {
    private ReviewService reviewService;

    private int reviewId;
    private int userId;

    public DeleteReviewCommand(ReviewService reviewService, int reviewId, int userId) {
        this.reviewService = reviewService;
        this.reviewId = reviewId;
        this.userId = userId;
    }

    @Override
    public boolean execute() {
        return reviewService.deleteReview(reviewId, userId);
    }
}

