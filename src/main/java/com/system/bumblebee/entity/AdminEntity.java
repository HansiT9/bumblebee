package com.system.bumblebee.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "admin")
@Data
public class AdminEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String email;
    @Column
    private String password;
}
