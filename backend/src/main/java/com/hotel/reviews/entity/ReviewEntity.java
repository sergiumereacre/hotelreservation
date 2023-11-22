package com.hotel.reviews.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.hotel.accounts.entity.AccountEntity;

import lombok.Data;

@Entity
@Data
// Concrete Command (Command Design Pattern)
// ReviewEntity should be the reciever
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;
    
    private String reviewText;
    private int rating;
    private LocalDateTime timeCreated;
    private LocalDateTime lastUpdated;

    @ManyToOne
    //@JoinColumn(name = "room_id", referencedColumnName = "room_id")
    private AccountEntity author;

    // Constructor, if needed
    // public ReviewEntity(int reviewId, int authorId, String reviewText, int rating, LocalDateTime timeCreated) {
    //    // Initialize variables here
    // }

    /*public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
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
    }*/

    public boolean editText(String reviewText) {
        // Implementation here
        return true;
    }

    // Additional methods...
}

