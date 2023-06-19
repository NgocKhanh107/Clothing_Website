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
@Table(name = "Product_Size")
public class ProductSize {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="Product_Id")
    Product product;

    @ManyToOne
    @JoinColumn(name="Size_Id")
    Size size;


    public ProductSize( Size size) {
        this.size = size;

    }

}
