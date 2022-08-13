package com.realestate.dto.request;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.realestate.domain.enums.TourRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TourRequestUpdateRequest {

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern =
            "MM/dd/yyyy HH:mm:ss", timezone = "Turkey")
    @NotNull(message = "Please select the reservation date")
    private LocalDateTime tourRequestFirstTime;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern =
            "MM/dd/yyyy HH:mm:ss", timezone = "Turkey")
    @NotNull(message = "Please select the reservation time")
    private LocalDateTime tourRequestLastTime;

    @DecimalMax(value = "20",message = "Adult selection '${validatedValue}' must be less than {value}")
    @DecimalMin(value = "0",message = "Adult selection '${validatedValue}' must be more than {value}")
    private int adult;

    @DecimalMax(value = "20",message = "Child selection '${validatedValue}' must be less than {value}")
    private int child;

}