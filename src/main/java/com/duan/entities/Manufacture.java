package com.duan.entities;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name="Manufactures")
public class Manufacture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(name = "Name")
    private String name;

    @Basic
    @Column(name = "Image")
    private String image;

    @Basic
    @Column(name = "Information")
    private String information;

    public Manufacture(String name, String image, String information) {
        this.name = name;
        this.image = image;
        this.information = information;
    }
}
