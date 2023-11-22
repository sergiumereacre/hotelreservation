package com.hotel.reviews.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.reviews.entity.ReviewEntity;
import com.hotel.reviews.service.ReviewService;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<Boolean> writeReview(@RequestParam int userId,
            @RequestParam String resRef,
            @RequestParam String reviewText,
            @RequestParam int rating) {
        boolean success = reviewService.writeReview(userId, resRef, reviewText, rating);
        return ResponseEntity.ok(success);
    }

    @PutMapping("/{reviewId}/editText")
    public ResponseEntity<Boolean> editReviewText(@PathVariable Long reviewId,
            @RequestParam Long userId,
            @RequestParam String reviewText) {
        boolean success = reviewService.editReviewText(reviewId, userId, reviewText);
        return ResponseEntity.ok(success);
    }

        @PutMapping("/{reviewId}/editRating")
    public ResponseEntity<Boolean> editReviewRating(@PathVariable Long reviewId,
            @RequestParam Long userId,
            @RequestParam int rating) {
        boolean success = reviewService.editReviewRating(reviewId, userId, rating);
        return ResponseEntity.ok(success);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Boolean> deleteReview(@PathVariable Long reviewId,
            @RequestParam Long userId) {
        boolean success = reviewService.deleteReview(reviewId, userId);
        return ResponseEntity.ok(success);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<ReviewEntity>> getReviewsByUser(@PathVariable Long userId) {
        List<ReviewEntity> reviews = reviewService.getReviewList(userId);
        return ResponseEntity.ok(reviews);
    }
}
