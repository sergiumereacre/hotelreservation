package com.hotel.discounts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.discounts.entity.DiscountDecoratorEntity;

@Repository
public interface DiscountRepository extends JpaRepository<DiscountDecoratorEntity, String>  {

}
