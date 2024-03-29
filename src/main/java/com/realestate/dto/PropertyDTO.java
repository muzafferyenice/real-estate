package com.realestate.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.realestate.domain.Property;
import com.realestate.domain.enums.Category;
import com.realestate.domain.enums.PropertyStatus;
import com.realestate.domain.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PropertyDTO {

        private Long id;

        private Long agentId;

        private String title;

        private String description;

        private Category category;

        private Type type;

        private int bedrooms;

        private int bathrooms;

        private int garages;

        private double area;

        private double price;

        private String location;

        private String address;

        private String country;

        private String city;

        private String district;

        private long likes;

        private  long visitCount;

       //private Set<String> image;

}
