package com.hotel.reviews.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hotel.reviews.entity.ReviewEntity;

@Service
// Reciever (Command Design Pattern)
public class ReviewMgt {

    public boolean writeReview(int userId, String resRef, String reviewText, int rating) {
        // Implementation here
        return true;
    }

    public boolean editReview(int userId, int reviewId, String reviewText, int rating) {
        // Implementation here
        return true;
    }

    public boolean canManageReview(int reviewId, int userId) {
        // Implementation here
        return true;
    }

    public boolean deleteReview(int reviewId, int userId) {
        // Implementation here
        return true;
    }

    public List<ReviewEntity> getReviewList(int userId) {
        // Implementation here
        return null;
    }

    // Dependencies and other methods...
}

