package com.realestate.repository;

import com.realestate.domain.Review;
import com.realestate.domain.User;
import com.realestate.dto.ReviewDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<ReviewDTO> findAllBy();
    Optional<List<Review>> findByPropertyIdId(Long userId);
    Optional<List<Review>> findByUserIdId(Long userId);




}
