package com.duan.entities;

import javax.persistence.*;
import lombok.*;

import java.util.Objects;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Product_Color")
public class ProductColor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String image;

    @ManyToOne
    @JoinColumn(name="Product_Id")
    Product product;

    @ManyToOne
    @JoinColumn(name="Color_Id")
    Color color;


    public ProductColor(String image,Product product, Color color) {
        this.image = image;
        this.product = product;
        this.color = color;
    }

    public ProductColor(Color color) {
        this.color = color;

    }
}
