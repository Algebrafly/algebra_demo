package com.algebra.jpa.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author al
 * @date 2021/2/5 14:03
 * @description
 */
@Data
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "salary")
    private BigDecimal salary;

    public Employee(){}

    public Employee(String firstName, String lastName, BigDecimal salary){
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
    }
}
