package com.realestate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.realestate.domain.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

}
