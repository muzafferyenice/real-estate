package com.realestate.dto;

import com.realestate.domain.Agent;
import com.realestate.domain.Property;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AgentDTO {

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
