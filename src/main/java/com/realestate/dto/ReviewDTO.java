package com.realestate.dto;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



import javax.validation.constraints.NotNull;

import javax.validation.constraints.Size;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ReviewDTO {

    @Size(max = 50)
    @NotNull(message = "Please provide your review")
    private String review;

    @NotNull(message = "Please provide your score")
    private Integer score;

}

