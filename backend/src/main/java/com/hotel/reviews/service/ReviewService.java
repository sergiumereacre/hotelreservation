package com.hotel.reviews.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.accounts.entity.AccountEntity;
import com.hotel.accounts.service.AccountService;
import com.hotel.reservations.interfaces.IReservationMgt;
import com.hotel.reviews.entity.ReviewEntity;
import com.hotel.reviews.repository.ReviewRepository;

@Service
// Reciever (Command Design Pattern)
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private AccountService accountService;

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
        ReviewEntity newReview = new ReviewEntity();
        newReview.setAuthor(accountService.getGuestById(userId));
        newReview.setReviewText(reviewText);
        newReview.setRating(rating);
        newReview.setTimeCreated(LocalDateTime.now());
        newReview.setLastUpdated(LocalDateTime.now());

        reviewRepository.save(newReview);
        return true;
    }

    // Method to edit an existing review
    public boolean editReview(Long userId, Long reviewId, String reviewText, int rating) {
        // Check if review exists and can be managed by the user
        if (!canManageReview(reviewId, userId)) {
            return false;
        }

        ReviewEntity review = reviewRepository.findById(reviewId).orElse(null);
        if (review == null) {
            return false;
        }
        review.setReviewText(reviewText);
        review.setRating(rating);
        review.setLastUpdated(LocalDateTime.now());

        return true;
    }

    // Method to check if a user can manage a review
    public boolean canManageReview(Long reviewId, Long userId) {
        ReviewEntity review = reviewRepository.findById(userId).orElse(null);

        return review != null && review.getAuthor().getId() == userId;
    }

    // Method to delete a review
    public boolean deleteReview(Long reviewId, Long userId) {
        if (!canManageReview(reviewId, userId)) {
            return false;
        }

        reviewRepository.deleteById(reviewId);
        return true;
    }

    // Method to get a list of reviews by a user
    public List<ReviewEntity> getReviewList(Long userId) {
        // List<ReviewEntity> userReviews = new ArrayList<>();
        // for (ReviewEntity review : reviewMap.values()) {
        //     if (review.getAuthorId() == userId) {
        //         userReviews.add(review);
        //     }
        // }
        
        return reviewRepository.findReviewsByAuthor(userId);
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

