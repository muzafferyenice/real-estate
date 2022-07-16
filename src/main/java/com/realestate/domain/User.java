package com.realestate.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tbl_user")
public class User {
    //kemal beyden
    //micro service bekir bey anlatirim demisti
    //kendinize zaman taninyip front end e yogunlasin inglizce calisin yd den is almaya bakin
    //dto entity de hatalar olursa response ve requestler de entity kullnmamamiz gerekir.
    //enttity leri dto lara maplayip dto lara cevirir
    //unit testleri nasil ekleriz? cok arastirma gerekir en son bakalim
    //tdd test driving developmet arastirilmali interview icin
    //githup ile baslayalim micro commit yapalim
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable=false)
    private String firstName;

    @Column(length = 50, nullable=false)
    private String lastName;

    @Column(length=20,nullable = false,unique = true)
    private String email;

    @Column(length =120,nullable=false )
    private String password;


    @Column(length = 14,nullable = false)
    private String phoneNumber;

    @Column(length = 100,nullable = false)
    private String address;

    @Column(length=15,nullable = false)
    private String zipCode;

    @Column(nullable = false)
    private Boolean builtIn=false;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="tbl_user_roles",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id")
    )
    private Set<Role> roles=new HashSet<>();

    @OneToMany(mappedBy = "userId")
    private Set<Review> reviews=new HashSet<>();

    @OneToMany(mappedBy = "userId")
    private Set<TourRequest> tourRequests=new HashSet<>();
}
