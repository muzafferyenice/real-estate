package com.realestate.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.realestate.domain.enums.TourRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TourRequestDTO {

    private LocalDateTime tourRequestFirstTime;

    private LocalDateTime tourRequestLastTime;

    private int adult;

    private int child;

    private TourRequestStatus status;
}
