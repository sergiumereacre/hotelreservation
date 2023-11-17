package com.hotel.reviews.entity;

import javax.persistence.Entity;

import com.hotel.reviews.interfaces.ReviewCommand;
import com.hotel.reviews.service.ReviewService;

@Entity
// Concrete Command (Command Design Pattern)
public class DeleteReviewCommand implements ReviewCommand {
    private ReviewService reviewMgt;
    private int reviewId;
    private int userId;

    public DeleteReviewCommand(ReviewService reviewMgt, int reviewId, int userId) {
        this.reviewMgt = reviewMgt;
        this.reviewId = reviewId;
        this.userId = userId;
    }

    @Override
    public boolean execute() {
        return reviewMgt.deleteReview(reviewId, userId);
    }
}

