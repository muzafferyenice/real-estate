package com.realestate.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Table(name="tbl_image")
@Entity
public class Image {

    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid", strategy="uuid2")
    private String id;


    private String featured;

    @JsonIgnore
    @Lob
    private byte[] data;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property propertyId;

    public Image(String name, String type,byte[] data) {
        this.featured=featured;
        this.data=data;
    }
}
