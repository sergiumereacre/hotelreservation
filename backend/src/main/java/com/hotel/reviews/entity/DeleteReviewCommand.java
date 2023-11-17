package com.hotel.reviews.entity;

import com.hotel.reviews.interfaces.ReviewCommand;
import com.hotel.reviews.service.ReviewMgt;

// Concrete Command (Command Design Pattern)
public class DeleteReviewCommand implements ReviewCommand {
    private ReviewMgt reviewMgt;
    private int reviewId;
    private int userId;

    public DeleteReviewCommand(ReviewMgt reviewMgt, int reviewId, int userId) {
        this.reviewMgt = reviewMgt;
        this.reviewId = reviewId;
        this.userId = userId;
    }

    @Override
    public boolean execute() {
        return reviewMgt.deleteReview(reviewId, userId);
    }
}

