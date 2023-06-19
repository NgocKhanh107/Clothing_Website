package com.duan.entities;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String position;

    @Column(name="Startday")
    private String startDay;

    @ManyToOne
    @JoinColumn(name="User_Id")
    Users user;
    public Employee(String position, String startDay, Users user, Integer id) {
        this.position = position;
        this.startDay = startDay;
        this.user = user;
        this.id = id;
    }
    public Employee(String position, String startDay, Users user) {
        this.position = position;
        this.startDay = startDay;
        this.user = user;

    }
}
