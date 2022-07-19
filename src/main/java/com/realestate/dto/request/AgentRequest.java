package com.realestate.dto.request;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgentRequest {


    @Size(max = 50)
    @NotNull(message = "Please provide your first name")
    private String firstName;

    @Size(max = 50)
    @NotNull(message = "Please provide your last name")
    private String lastName;


    @Pattern(regexp = "^((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$",
            message = "Please provide valid phone number")
    @Size(min = 14, max = 14)
    @NotNull(message = "Please provide your phone number")
    private String phoneNumber;

    //TODO mail i nasil kontrol ediyor
    @Email(message = "Please provide valid email")
    @Size(min = 5, max = 200)
    @NotNull(message = "Please provide your email")
    private String email;


}
