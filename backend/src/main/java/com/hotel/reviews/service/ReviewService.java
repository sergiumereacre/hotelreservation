package com.hotel.reviews.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.accounts.service.AccountService;
import com.hotel.reservations.service.ReservationService;
import com.hotel.reviews.entity.EditReviewRatingCommand;
import com.hotel.reviews.entity.EditReviewTextCommand;
import com.hotel.reviews.entity.ReviewEntity;
import com.hotel.reviews.entity.ReviewInvoker;
import com.hotel.reviews.repository.ReviewRepository;

// Client (Command Design Pattern)
@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ReservationService reservationService;

    ReviewInvoker reviewInvoker = new ReviewInvoker();

    // Method to write a new review
    public boolean writeReview(int userId, String resRef, String reviewText, int rating) {
        // Preconditions check (simplified for demonstration)
        if (reviewText == null || reviewText.isEmpty()) {
            return false;
        }

        if (reservationService.getReservation(resRef) == null) {
            return false;
        }

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

    public boolean editReviewText(Long userId, Long reviewId, String reviewText) {
        // Check if review exists and can be managed by the user
        if (!canManageReview(reviewId, userId)) {
            return false;
        }

        ReviewEntity review = reviewRepository.findById(reviewId).orElse(null);
        if (review == null) {
            return false;
        }
        
        reviewInvoker.setCommand(new EditReviewTextCommand(userId, review, reviewText));
        review.setLastUpdated(LocalDateTime.now());
        reviewInvoker.executeCommand();

        reviewRepository.save(review);

        return true;
    }

    public boolean editReviewRating(Long userId, Long reviewId, int rating) {
        // Check if review exists and can be managed by the user
        if (!canManageReview(reviewId, userId)) {
            return false;
        }

        ReviewEntity review = reviewRepository.findById(reviewId).orElse(null);
        if (review == null) {
            return false;
        }

        reviewInvoker.setCommand(new EditReviewRatingCommand(userId, review, rating));
        review.setLastUpdated(LocalDateTime.now());
        reviewInvoker.executeCommand();

        reviewRepository.save(review);

        return true;
    }

    // Method to check if a user can manage a review
    public boolean canManageReview(Long reviewId, Long userId) {
        ReviewEntity review = reviewRepository.findById(reviewId).orElse(null);

        return review != null && review.getAuthor().getId().equals(userId);
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
        return reviewRepository.findReviewsByAuthor(userId);
    }
}

