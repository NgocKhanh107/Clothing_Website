package com.duan.entities;

import javax.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name="Categories")
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String image;

    @OneToMany(mappedBy = "category")
    List<ProductCate> productManu;

    public Category(String name, String image) {
        this.name = name;
        this.image = image;
    }
}
