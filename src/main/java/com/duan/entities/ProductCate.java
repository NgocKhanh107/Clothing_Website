package com.duan.entities;

import javax.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name="Product_Cate")
public class ProductCate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="Product_Id")
    Product product;

    @ManyToOne
    @JoinColumn(name="Cate_Id")
    Category category;

    Long count;
    public ProductCate(Product product, Category category) {
        this.product = product;
        this.category = category;
    }
    public ProductCate( Category category,Long count) {

        this.category = category;
        this.count = count;
    }
}
