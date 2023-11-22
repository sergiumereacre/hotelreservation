package com.hotel.reviews.entity;

import javax.persistence.Entity;

@Entity
// Concrete Command (Command Design Pattern)
public class DeleteReviewCommand extends ReviewCommandEntity {
    //@Id
    private long deleteReviewCommandId;

    public DeleteReviewCommand(Long reviewId, Long author) {
        this.reviewId = reviewId;
        this.author = author;
    }

    @Override
    public boolean execute() {
        return false; //reviewService.deleteReview(reviewId, userId);
    }
}

