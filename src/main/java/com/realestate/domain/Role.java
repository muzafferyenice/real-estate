package com.realestate.domain;

import com.realestate.domain.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="tbl_role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //@Enumerated(EnumType.STRING)//string yazdik ki yazi olarak donsun roletype nini ordinal dersen numarasini yazar
    @Column(length=20)
    private RoleType name;

}
