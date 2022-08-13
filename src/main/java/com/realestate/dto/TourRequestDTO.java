package com.realestate.dto;

import com.realestate.domain.enums.TourRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TourRequestDTO {

    //private Long id;

//    private PropertyDTO propertyId;
//
//    private Long userId;

    private LocalDateTime tourRequestFirstTime;

    private LocalDateTime tourRequestLastTime;

    private int adult;

    private int child;

    private TourRequestStatus status;
}