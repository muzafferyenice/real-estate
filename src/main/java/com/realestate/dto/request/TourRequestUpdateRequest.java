package com.realestate.dto.request;


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
public class TourRequestUpdateRequest {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="dd/MM/yyyy", timezone = "Turkey")
    private LocalDateTime tourRequestFirstTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="HH:mm", timezone = "Turkey")
    private LocalDateTime tourRequestLastTime;

    @DecimalMax(value = "20",message = "Adult selection '${validatedValue}' must be less than {value}")
    @DecimalMin(value = "0",message = "Adult selection '${validatedValue}' must be more than {value}")
    private int adult;

    @DecimalMax(value = "20",message = "Child selection '${validatedValue}' must be less than {value}")
    private int child;

    private TourRequestStatus status;
}
