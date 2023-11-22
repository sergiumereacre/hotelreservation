package com.hotel.reviews.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hotel.accounts.entity.AccountEntity;
import com.hotel.reservations.interfaces.IReservationMgt;
import com.hotel.reviews.entity.ReviewEntity;

@Service
// Reciever (Command Design Pattern)
public class ReviewService {
    // A simple in-memory store for demonstration purposes
    private Map<Integer, ReviewEntity> reviewMap = new HashMap<>();

    // Dependencies
    private AccountEntity guestMgt;
    private IReservationMgt reservationMgt;

    // Method to write a new review
    public boolean writeReview(int userId, String resRef, String reviewText, int rating) {
        // Preconditions check (simplified for demonstration)
        if (reviewText == null || reviewText.isEmpty()) {
            return false;
        }
        // Assuming guestMgt and reservationMgt are used here for further checks
        // ...

        // Create and store the review
        int reviewId = generateReviewId(); // Method to generate a unique review ID
        ReviewEntity newReview = new ReviewEntity();
        newReview.setReviewId(reviewId);
        newReview.setAuthorId(userId);
        newReview.setReviewText(reviewText);
        newReview.setRating(rating);
        newReview.setTimeCreated(LocalDateTime.now());
        newReview.setLastUpdated(LocalDateTime.now());

        reviewMap.put(reviewId, newReview);
        return true;
    }

    // Method to edit an existing review
    public boolean editReview(int userId, int reviewId, String reviewText, int rating) {
        // Check if review exists and can be managed by the user
        if (!canManageReview(reviewId, userId)) {
            return false;
        }

        ReviewEntity review = reviewMap.get(reviewId);
        review.setReviewText(reviewText);
        review.setRating(rating);
        review.setLastUpdated(LocalDateTime.now());

        return true;
    }

    // Method to check if a user can manage a review
    public boolean canManageReview(int reviewId, int userId) {
        ReviewEntity review = reviewMap.get(reviewId);
        return review != null && review.getAuthorId() == userId;
    }

    // Method to delete a review
    public boolean deleteReview(int reviewId, int userId) {
        if (!canManageReview(reviewId, userId)) {
            return false;
        }

        reviewMap.remove(reviewId);
        return true;
    }

    // Method to get a list of reviews by a user
    public List<ReviewEntity> getReviewList(int userId) {
        List<ReviewEntity> userReviews = new ArrayList<>();
        for (ReviewEntity review : reviewMap.values()) {
            if (review.getAuthorId() == userId) {
                userReviews.add(review);
            }
        }
        return userReviews;
    }

    // Helper method to generate a unique review ID
    private int generateReviewId() {
        return reviewMap.size() + 1;
    }

    // Setters for dependencies
    public void setGuestMgt(AccountEntity guestMgt) {
        this.guestMgt = guestMgt;
    }

    public void setReservationMgt(IReservationMgt reservationMgt) {
        this.reservationMgt = reservationMgt;
    }

    // Dependencies and other methods...
}

