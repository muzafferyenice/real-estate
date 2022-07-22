package com.realestate.domain;

import com.realestate.domain.enums.ReviewStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //TODO
    @Column(length = 50, nullable = false)
    private Long review;

    @Column(length = 50, nullable = false)
    private LocalDateTime createDate=LocalDateTime.now();

    @Column(length = 50, nullable = false)
    private int score;

    @Column(length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private ReviewStatus status;


    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property propertyId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

}
