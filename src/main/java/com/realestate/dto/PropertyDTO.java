package com.realestate.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.realestate.domain.enums.Category;
import com.realestate.domain.enums.PropertyStatus;
import com.realestate.domain.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PropertyDTO {

	@Size(max = 30)
	@NotNull(message = "Please provide title")
	private String title;

	@Size(max = 200)
	@NotNull(message = "Please provide description")
	private String description;

	@NotNull
	private Category category;

	@NotNull
	private Type type;

	@NotNull
	private int bedrooms;

	@NotNull
	private int bathrooms;

	@NotNull
	private int garages;

	@NotNull
	private double area;

	@NotNull
	private double price;

	@Size(max = 30)
	@NotNull(message = "Please provide location")
	private String location;
	
	@Size(max = 100)
	@NotNull(message = "Please provide address")
	private String address;

	@Size(max = 30)
	@NotNull(message = "Please provide country")
	private String country;

	@Size(max = 30)
	@NotNull(message = "Please provide city")
	private String city;

	@Size(max = 30)
	@NotNull(message = "Please provide district")
	private String district;

}
