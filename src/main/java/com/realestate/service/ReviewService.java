package com.realestate.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.realestate.exception.ResourceNotFoundException;
import com.realestate.exception.message.ErrorMessage;
import com.realestate.domain.Property;
import com.realestate.domain.Review;
import com.realestate.domain.User;
import com.realestate.domain.enums.ReviewStatus;
import com.realestate.dto.ReviewDTO;
import com.realestate.dto.mapper.ReviewMapper;
import com.realestate.dto.request.ReviewRequest;
import com.realestate.repository.PropertyRepository;
import com.realestate.repository.ReviewRepository;
import com.realestate.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReviewService {

	private ReviewRepository reviewRepository;
	private UserRepository userRepository;
	private PropertyRepository propertyRepository;
	private ReviewMapper reviewMapper;
	
	public void createReview(Long userId, @Valid ReviewDTO reviewDTO, Long propertyId) {
		User user = userRepository.findById(userId).orElseThrow(()->new 
				ResourceNotFoundException(String.format(ErrorMessage.REVIEW_NOT_FOUND_MESSAGE, userId)));
		
		Property property= propertyRepository.findById(propertyId).orElseThrow(()->
         new ResourceNotFoundException(String.format(ErrorMessage.REVIEW_NOT_FOUND_MESSAGE, propertyId)));
		
		
		Review review = reviewMapper.ReviewDTOToReview(reviewDTO);
		
		review.setPropertyId(property);	
		review.setCreateDate(LocalDateTime.now());
		review.setStatus(ReviewStatus.PENDING);
		review.setUserId(user);
		
		reviewRepository.save(review);
		
		
	}

	public void deleteReview(Long id) {
		
		Review review = reviewRepository.findById(id).orElseThrow(()-> new 
				ResourceNotFoundException(String.format(ErrorMessage.REVIEW_NOT_FOUND_MESSAGE, id)));
		
		reviewRepository.delete(review);
		
	}

	public ReviewDTO getReview(Long id) {
		Review review = reviewRepository.findById(id).orElseThrow(()-> new 
				ResourceNotFoundException(String.format(ErrorMessage.REVIEW_NOT_FOUND_MESSAGE, id)));
		
		ReviewDTO reviewDTO = reviewMapper.ReviewToReviewDTO(review);
		return reviewDTO;
	}

	public List<Review> getReviews(Long propertyId) {
		
		Property property = propertyRepository.findById(propertyId).orElseThrow(()-> new 
				ResourceNotFoundException(String.format(ErrorMessage.REVIEW_NOT_FOUND_MESSAGE, propertyId)));
		return null;
	}

}
