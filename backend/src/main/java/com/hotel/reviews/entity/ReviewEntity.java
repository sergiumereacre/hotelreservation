package com.hotel.reviews.entity;

import java.time.LocalDateTime;

// Concrete Command (Command Design Pattern)
public class ReviewEntity {
    private int reviewId;
    private int authorId;
    private String reviewText;
    private int rating;
    private LocalDateTime timeCreated;
    private LocalDateTime lastUpdated;

    // Constructor, if needed
    // public Review(int reviewId, int authorId, String reviewText, int rating, LocalDateTime timeCreated) {
    //    // Initialize variables here
    // }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(LocalDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public boolean editText(String reviewText) {
        // Implementation here
        return true;
    }

    // Additional methods...
}

