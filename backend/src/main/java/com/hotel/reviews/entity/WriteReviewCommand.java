package com.hotel.reviews.entity;

import com.hotel.reviews.interfaces.ReviewCommand;
import com.hotel.reviews.service.ReviewMgt;

// Concrete Command (Command Design Pattern)
public class WriteReviewCommand implements ReviewCommand {
    private ReviewMgt reviewMgt;
    private int userId;
    private String resRef;
    private String reviewText;
    private int rating;

    public WriteReviewCommand(ReviewMgt reviewMgt, int userId, String resRef, String reviewText, int rating) {
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

