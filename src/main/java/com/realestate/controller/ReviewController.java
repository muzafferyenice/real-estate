package com.realestate.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realestate.dto.response.ResponseMessage;
import com.realestate.service.ReviewService;
import com.realestate.domain.Property;
import com.realestate.dto.request.ReviewRequest;
import com.realestate.dto.response.RealEstateResponse;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/review")
public class ReviewController {
	
	private ReviewService reviewService ;

	@PostMapping("/add/{propertyId}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('CUSTOMER')")
	public ResponseEntity<RealEstateResponse> addReview(HttpServletRequest request, @Valid @RequestBody ReviewRequest reviewRequest, @PathVariable Long propertyId ) {
		Long userId= (Long) request.getAttribute("id");
		
		reviewService.createReview(userId, reviewRequest, propertyId);
		
		
		RealEstateResponse response=new RealEstateResponse();
		response.setMessage(ResponseMessage.REVIEW_SAVED_RESPONSE_MESSAGE);
		response.setSuccess(true);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	
		
	}
	

}
