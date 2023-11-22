package com.hotel.reviews.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hotel.reviews.entity.ReviewEntity;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
    @Query("SELECT r FROM ReservationEntity r WHERE r.room = :roomId AND  r.startDate <= :endDate AND r.endDate >= :startDate")
        Optional<ReviewEntity> findReviewsByAuthor(@Param("author") Integer roomId,
                        @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
