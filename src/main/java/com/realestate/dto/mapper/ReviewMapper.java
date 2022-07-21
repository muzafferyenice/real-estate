package com.realestate.dto.mapper;

import com.realestate.domain.Review;
import com.realestate.dto.ReviewDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    Review reviewDTOToReview(ReviewDTO reviewDTO);

    ReviewDTO reviewToReviewDTO(Review review);

    List<ReviewDTO> map(List<Review> review);
}
