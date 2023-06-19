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
@Table(name = "Address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(name = "Firstname")
    private String firstname;

    @Basic
    @Column(name = "Lastname")
    private String lastname;

    @Basic
    @Column(name = "Email")
    private String email;

    @Basic
    @Column(name = "Phone")
    private String phone;

    @Basic
    @Column(name = "Provincial")
    private String provincial;

    @Basic
    @Column(name = "Address")
    private String address;

    @ManyToOne
    @JoinColumn(name = "User_Id")
    private Users user;

    public Address(String firstname, String lastname, String email, String phone, String provincial, String address,
                   Users user) {
        super();
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.provincial = provincial;
        this.address = address;
        this.user = user;
    }
}
