package com.realestate.dto.request;

import javax.validation.constraints.NotNull;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequest {

    @Size(max = 50)
    @NotNull(message = "Please provide your review")
     private String review;


    @NotNull(message = "Please provide your score")
     private Integer score;


}