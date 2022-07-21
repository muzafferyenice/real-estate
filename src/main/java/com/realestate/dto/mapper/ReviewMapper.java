package com.realestate.dto.mapper;


import org.mapstruct.Mapper;

import com.realestate.domain.Review;
import com.realestate.dto.ReviewDTO;



@Mapper(componentModel= "spring")
//mapper la map yapacagimizi soyluyoruz
//componentModel spring ile bu interface de bean uretip spring container a koyacagimizi soyluyoruz
//(componentModel= "spring") bizim enjekte edebilmemizi sagliyor
public interface ReviewMapper {
    ReviewDTO ReviewToReviewDTO(Review review);
    
    Review ReviewDTOToReview(ReviewDTO reviewDTO);

   


}
