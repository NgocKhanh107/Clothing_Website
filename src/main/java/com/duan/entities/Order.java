package com.duan.entities;

import javax.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name="Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(name = "Quality")
    private int quality;

    @Basic
    @Column(name = "Date")
    private String date;

    @Basic
    @Column(name = "Method")
    private boolean method;

    @Basic
    @Column(name = "Name")
    private String name;

    @Basic
    @Column(name = "Status")
    private Boolean status;

    @ManyToOne
    @JoinColumn(name="Color_Id", nullable = true)
    Color color;

    @ManyToOne
    @JoinColumn(name="Size_Id", nullable = true)
    Size size;

    @ManyToOne
    @JoinColumn(name="Address_Id")
    Address address;

    @ManyToOne
    @JoinColumn(name="Product_Id")
    Product product;

    public Order(String name, boolean status, boolean method, int quality, String date, Color color, Size size,
                 Address address, Product product) {
        super();
        this.name = name;
        this.status = status;
        this.method = method;
        this.quality = quality;
        this.date = date;
        this.color = color;
        this.size = size;
        this.address = address;
        this.product = product;
    }

}
