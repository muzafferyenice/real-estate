package com.realestate.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realestate.dto.response.ResponseMessage;
import com.realestate.service.ReviewService;
import com.realestate.domain.Property;
import com.realestate.domain.Review;
import com.realestate.dto.ReviewDTO;
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
	public ResponseEntity<RealEstateResponse> addReview(HttpServletRequest request, @Valid @RequestBody ReviewDTO reviewDTO, @PathVariable Long propertyId ) {
		
		
		Long userId= (Long) request.getAttribute("id");
		
		reviewService.createReview(userId, reviewDTO, propertyId);
		
		
		RealEstateResponse response=new RealEstateResponse();
		response.setMessage(ResponseMessage.REVIEW_SAVED_RESPONSE_MESSAGE);
		response.setSuccess(true);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	
		
	}
	
	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('CUSTOMER')")
	public ResponseEntity<RealEstateResponse> deleteReview(@PathVariable Long id){
		
		reviewService.deleteReview(id);
		
		RealEstateResponse response = new RealEstateResponse();
		response.setMessage("review is deleted");
		response.setSuccess(true);
			
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('CUSTOMER')")
	public ResponseEntity<ReviewDTO> getReview(Long id){
		ReviewDTO reviewDTO = reviewService.getReview(id);
		
		
		return ResponseEntity.ok(reviewDTO);
	}
	

}
