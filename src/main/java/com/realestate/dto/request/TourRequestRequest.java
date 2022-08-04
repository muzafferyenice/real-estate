package com.realestate.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TourRequestRequest {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="dd/MM/yyyy", timezone = "Turkey")
    @NotNull(message = "Please select the reservation date")
    private LocalDateTime tourRequestFirstTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="HH:mm", timezone = "Turkey")
    @NotNull(message = "Please select the reservation time")
    private LocalDateTime tourRequestLastTime;

    @DecimalMax(value = "20",message = "Adult selection '${validatedValue}' must be less than {value}")
    @DecimalMin(value = "0",message = "Adult selection '${validatedValue}' must be more than {value}")
    private int adult;

    @DecimalMax(value = "20",message = "Child selection '${validatedValue}' must be less than {value}")
    private int child;
}
