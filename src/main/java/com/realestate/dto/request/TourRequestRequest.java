package com.realestate.dto.request;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TourRequestRequest {

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

//    @Size(max = 50, message = "Size is exceeded")
//    @NotNull(message = "Please enter your full name")
//    @Column(nullable = false, length = 50)
//    private String contactNameSurname;
//
//    @Pattern(regexp = "^((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$",
//            message = "Please enter valid phone number")
//    @Size(min = 14, max= 14, message = "Phone number should be exact 10 characters")
//    @NotNull(message = "Please enter your phone number")
//    @Column(nullable = false, length = 14)
//    private String contactPhone;
//
//    @Email(message = "Please enter valid email")
//    @NotNull(message = "Please enter your email")
//    @Size(min = 5, max = 254)
//    @Column(nullable = false, unique = true, length = 254)
//    private String contactEmail;
//    private Boolean approved;
}