package com.realestate.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.realestate.domain.enums.Category;
import com.realestate.domain.enums.PropertyStatus;
import com.realestate.domain.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tbl_property")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 200, nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private Category category;

    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false)
    private Type type;

    @Column(nullable = false)
    private int bedrooms;

    @Column(nullable = false)
    private int bathrooms;

    @Column(nullable = false)
    private int garages;

    @Column(length = 50, nullable = false)
    private double area;

    @Column(length = 50, nullable = false)
    private double price;

    @Column(length = 50, nullable = false)
    private String location;

    @Column(length = 50, nullable = false)
    private String address;

    @Column(length = 50, nullable = false)
    private String country;

    @Column(length = 50, nullable = false)
    private String city;

    @Column(length = 50, nullable = false)
    private String district;

    @Column(length = 50, nullable = false)
    private LocalDateTime createDate = LocalDateTime.now();

    @Column(length = 50, nullable = false)//bunu ayri bir entity yapiniz
    private long likes;

    @Column(length = 50, nullable = false)
    private  long visitCount;//primitive d t

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private PropertyStatus status=PropertyStatus.ACTIVE;

   //JoinColumn one tarafinda kalmayacak many tarafina atilacak table orda olusturulcak
   //@JoinColumn(name = "tour_id")//iliski kurarkene bu bir container a atmalisin yani Set e
    @OneToMany(mappedBy = "propertyId")
    private Set<TourRequest> tourRequests=new HashSet<>();


    @OneToMany(mappedBy = "propertyId")
    private Set<Image> images=new HashSet<>();

    @OneToMany(mappedBy = "propertyId")
    private Set<Review> reviews=new HashSet<>();

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="tbl_property_propdetails",
            joinColumns =  @JoinColumn(name = "propertyDetail_id"),
            inverseJoinColumns = @JoinColumn(name="property_id"))

    private Set<PropertyDetail> propertyDetail=new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "agent_id")
    private Agent agentId;

}
