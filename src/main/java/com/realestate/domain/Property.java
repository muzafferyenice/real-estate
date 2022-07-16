package com.realestate.domain;

import com.realestate.domain.enums.PropertyStatus;
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
    private String category;

    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false)
    private String type;

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
    private Long agentId;
    @Column(length = 50, nullable = false)
    private LocalDateTime createDate;
    @Column(length = 50, nullable = false)
    private int likes;

    @Column(length = 50, nullable = false)
    private static int visitCount;

    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false)
    private PropertyStatus status;

    @OneToMany
    @JoinColumn(name = "tour_id")//iliski kurarkene bu bir container a atmalisin yani Set e
    //private TourRequest tourId;
    private Set<TourRequest> tourRequests=new HashSet<>();

    @OneToMany
    @JoinColumn(name = "image_id")
    private Set<Image> images=new HashSet<>();

    @OneToMany
    @JoinColumn(name = "review_id")
    private Set<Review> reviews=new HashSet<>();

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="tbl_property_propdetails",
            joinColumns =  @JoinColumn(name = "propertyDetail_id"),
            inverseJoinColumns = @JoinColumn(name="property_id"))

    private Set<PropertyDetail> propertyDetail=new HashSet<>();


}
