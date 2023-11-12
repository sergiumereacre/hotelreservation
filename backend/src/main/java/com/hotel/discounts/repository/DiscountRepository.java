package com.hotel.discounts.repository;

import org.springframework.stereotype.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.hotel.discounts.entity.DiscountDecoratorEntity;


// Unsure what to add here
@Repository
public interface DiscountRepository extends JpaRepository<DiscountDecoratorEntity, String>  {

    
}
