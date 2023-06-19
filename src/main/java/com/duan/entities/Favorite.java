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
@Table(name="Favorites")
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(name = "Date")
    private Date date;

    @ManyToOne
    @JoinColumn(name="User_Id")
    Users user;

    @ManyToOne
    @JoinColumn(name="Product_Id")
    Product product;

    public Favorite(Date date, Users user, Product product) {
        super();
        this.date = date;
        this.user = user;
        this.product = product;
    }
}
