package com.realestate.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.realestate.domain.enums.TourRequestStatus;
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
@Table(name="tbl_tourRequest")
public class TourRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 50, nullable = false)
    private Long id;

    @Column(length = 50, nullable = false)
    private LocalDateTime tourRequestFirstTime;

    @Column(length = 50, nullable = false)
    private LocalDateTime tourRequestLastTime;

    @Column(length = 50, nullable = false)
    private int adult;

    @Column(length = 50)
    private int child;

    @Enumerated(EnumType.STRING)
    @Column(length=30,nullable=false)
    private TourRequestStatus status;


    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property propertyId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;
}
