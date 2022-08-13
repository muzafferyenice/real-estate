package com.realestate.repository;

import com.realestate.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  ReviewRepository extends JpaRepository<Review,Long> {


}
