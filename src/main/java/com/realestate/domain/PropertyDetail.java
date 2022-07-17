package com.realestate.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_property_detail")
public class PropertyDetail {
	
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable=false)
    private String   title;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="tbl_property_propdetails",
            joinColumns =  @JoinColumn(name = "propertyDetail_id"),
            inverseJoinColumns = @JoinColumn(name="property_id"))

    private Set<Property> properties=new HashSet<>();

}
