package com.realestate.service;


import com.realestate.domain.Property;
import com.realestate.domain.Review;
import com.realestate.domain.Role;
import com.realestate.domain.User;
import com.realestate.domain.enums.ReviewStatus;
import com.realestate.dto.request.ReviewRequest;
import com.realestate.exception.ResourceNotFoundException;
import com.realestate.exception.message.ErrorMessage;
import com.realestate.repository.PropertyRepository;
import com.realestate.repository.ReviewRepository;
import com.realestate.repository.UserRepository;
import com.realestate.security.service.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    private PropertyRepository propertyRepository;

    @Autowired
    private UserRepository userRepository;


    public void createReview(Long propertyId,Long userId, ReviewRequest reviewRequest) {

        Property property= propertyRepository.findById(propertyId).orElseThrow(()->
                new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, propertyId)));

        User user= userRepository.findById(userId).orElseThrow(()->
                new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, userId)));

        Review review = new Review();
        review.setReview(reviewRequest.getReview());
        review.setScore(reviewRequest.getScore());
        review.setCreateDate(LocalDateTime.now());
        review.setPropertyId(property);
        review.setStatus(ReviewStatus.PENDING);
        review.setUserId(user);

        reviewRepository.save(review);


    }
}
