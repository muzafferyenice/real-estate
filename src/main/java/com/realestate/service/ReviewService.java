package com.realestate.service;


import com.realestate.domain.Property;
import com.realestate.domain.Review;
import com.realestate.domain.Role;
import com.realestate.domain.User;
import com.realestate.domain.enums.ReviewStatus;
import com.realestate.domain.enums.RoleType;
import com.realestate.dto.ReviewDTO;
import com.realestate.dto.mapper.ReviewMapper;
import com.realestate.exception.ResourceNotFoundException;
import com.realestate.exception.message.ErrorMessage;
import com.realestate.repository.PropertyRepository;
import com.realestate.repository.ReviewRepository;
import com.realestate.repository.RoleRepository;
import com.realestate.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
public class ReviewService {


    @Autowired
    private ReviewRepository reviewRepository;

    private PropertyRepository propertyRepository;

    @Autowired
    private UserRepository userRepository;

    private RoleRepository roleRepository;


    private ReviewMapper reviewMapper;

    public void createReview(Long propertyId, Long userId, ReviewDTO reviewDTO) {

        Property property = propertyRepository.findById(propertyId).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, propertyId)));

        User user = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessage.USERID_NOT_FOUND_MESSAGE, userId)));

        Review review = reviewMapper.reviewDTOToReview(reviewDTO);
        review.setPropertyId(property);
        review.setStatus(ReviewStatus.PUBLISHED);
        review.setUserId(user);

        reviewRepository.save(review);
    }


    public void deleteReview(Long id) {

        Review review = reviewRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessage.REVIEW_NOT_FOUND_MESSAGE, id)));

        reviewRepository.delete(review);

    }

    public ReviewDTO getReview(Long id) {
        Review review = reviewRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessage.REVIEW_NOT_FOUND_MESSAGE, id)));
        return reviewMapper.reviewToReviewDTO(review);
    }


    public void updateCustomerReview(Long propertyId, Long userId, ReviewDTO reviewDTO, Long reviewId) {
        Review foundReview = reviewRepository.findById(reviewId).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, reviewId)));

        Property property = propertyRepository.findById(propertyId).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, propertyId)));

        User user = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessage.USERID_NOT_FOUND_MESSAGE, userId)));


        if (!foundReview.getUserId().equals(user)) {
            throw new ResourceNotFoundException(String.format(ErrorMessage.USERWITH_NOT_FOUND_MESSAGE, userId));
        }

        Role role = roleRepository.findByName(RoleType.ROLE_ADMIN).orElseThrow(() -> new ResourceNotFoundException(
                String.format(ErrorMessage.ROLE_NOT_FOUND_MESSAGE, RoleType.ROLE_CUSTOMER.name())));

        Review review = reviewMapper.reviewDTOToReview(reviewDTO);

        if (user.getRoles().contains(role)) {
            review.setStatus(ReviewStatus.PENDING);
        } else {
            review.setStatus(foundReview.getStatus());
        }
        review.setId(foundReview.getId());
        review.setPropertyId(property);
        review.setUserId(user);

        reviewRepository.save(review);

    }


    public List<ReviewDTO> getReviews(Long propertyId,Long curretylUserId) {
        Property property=propertyRepository.findById(propertyId).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, propertyId)));
        Role role = roleRepository.findByName(RoleType.ROLE_CUSTOMER).orElseThrow(() -> new ResourceNotFoundException(
                String.format(ErrorMessage.ROLE_NOT_FOUND_MESSAGE, RoleType.ROLE_CUSTOMER.name())));

        User user = userRepository.findById(curretylUserId).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessage.USERID_NOT_FOUND_MESSAGE, curretylUserId)));


        if (user.getRoles().contains(role)) {

            List<Review> reviews = reviewRepository.findByUserIdId(curretylUserId).orElseThrow(() ->
                    new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, curretylUserId)));
            List<Review> review2=new ArrayList<>();

            for (Review review: reviews) {
                if(review.getPropertyId().getId().equals(propertyId)){
                    review2.add(review);
                }
            }

            return reviewMapper.map(review2);
        }else {
            List<Review> reviews = reviewRepository.findByPropertyIdId(propertyId).orElseThrow(() ->
                    new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, propertyId)));
            return reviewMapper.map(reviews);
        }
    }
}
