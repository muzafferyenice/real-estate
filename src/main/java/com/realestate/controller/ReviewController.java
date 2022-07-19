package com.realestate.controller;

import com.realestate.domain.Property;
import com.realestate.domain.Review;
import com.realestate.domain.User;
import com.realestate.dto.request.ReviewRequest;
import com.realestate.dto.response.RealEstateResponse;
import com.realestate.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/review")
@AllArgsConstructor
public class ReviewController {

    private ReviewService reviewService;


    @PostMapping("/add")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN')")
    public ResponseEntity<RealEstateResponse> createReview(@RequestParam(value = "userId") Long userId,
                                                             @RequestParam(value = "propertyId") Long propertyId,
                                                             @Valid @RequestBody ReviewRequest reviewRequest){

        reviewService.createReview(propertyId,userId,reviewRequest);

        RealEstateResponse realEstateResponse = new RealEstateResponse();
        realEstateResponse.setMessage("Your review is saved and pending");
        realEstateResponse.setSuccess(true);

        return new ResponseEntity<>(realEstateResponse, HttpStatus.CREATED);

    }



}
