package com.realestate.controller;

import com.realestate.domain.Review;
import com.realestate.domain.User;
import com.realestate.dto.ReviewDTO;
import com.realestate.dto.response.RealEstateResponse;
import com.realestate.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/review")
@AllArgsConstructor
public class ReviewController {

    private ReviewService reviewService;


    @PostMapping("/add/{id}")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN')")
    public ResponseEntity<RealEstateResponse> createReview(HttpServletRequest request,
                                                           @PathVariable("id") Long propertyId,
                                                           @Valid @RequestBody ReviewDTO reviewDTO){

         User userId= (User) request.getAttribute("id");

        reviewService.createReview(propertyId,userId,reviewDTO);

        RealEstateResponse realEstateResponse = new RealEstateResponse();
        realEstateResponse.setMessage("Your review is saved and pending");
        realEstateResponse.setSuccess(true);

        return new ResponseEntity<>(realEstateResponse, HttpStatus.CREATED);

    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN')")
    public ResponseEntity<RealEstateResponse> deleteReview(@PathVariable Long id){

        reviewService.deleteReview(id);

        RealEstateResponse realEstateResponse = new RealEstateResponse();
        realEstateResponse.setMessage("Your review is deleted");
        realEstateResponse.setSuccess(true);

        return new ResponseEntity<>(realEstateResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN')")
    public ResponseEntity<ReviewDTO> getReview(@PathVariable("id") Long id) {

      ReviewDTO reviewDTO=  reviewService.getReview(id);


        return ResponseEntity.ok(reviewDTO);
    }


    @PutMapping("/update")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN')")
    public ResponseEntity<RealEstateResponse> updateReview(HttpServletRequest request,
                                                                   @RequestParam(value = "propertyId") Long propertyId,
                                                                   @RequestParam(value = "reviewId") Long reviewId,
                                                                   @Valid @RequestBody ReviewDTO reviewDTO){
        Long userId= (Long) request.getAttribute("id");

        reviewService.updateReview(propertyId,userId,reviewDTO,reviewId);

        RealEstateResponse realEstateResponse = new RealEstateResponse();
        realEstateResponse.setMessage("Your review is updated");
        realEstateResponse.setSuccess(true);

        return new ResponseEntity<>(realEstateResponse, HttpStatus.OK);

    }

    @GetMapping("/all/{id}")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN')")
    public ResponseEntity<List<ReviewDTO>> getReviews(HttpServletRequest request,@PathVariable("id") Long propertyId) {


        Long curretylUserId = (Long) request.getAttribute("id");

       List<ReviewDTO> reviewDTO=  reviewService.getReviews(propertyId,curretylUserId);


        return ResponseEntity.ok(reviewDTO);
    }


}
