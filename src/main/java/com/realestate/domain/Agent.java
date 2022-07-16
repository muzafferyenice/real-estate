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
@Table(name="tbl_agent")
public class Agent {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable=false)
    private String firstName;

    @Column(length = 50, nullable=false)
    private String lastName;

    @Column(length=20,nullable = false,unique = true)
    private String email;

    @Column(length = 14,nullable = false)
    private String phoneNumber;


    @OneToMany(mappedBy = "agentId")
    private Set<Property> properties=new HashSet<>();






}
