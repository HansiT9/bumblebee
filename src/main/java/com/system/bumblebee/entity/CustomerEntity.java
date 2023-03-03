package com.system.bumblebee.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String fullName;
    @Column
    private String dob;
    @Column
    private String nic;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private Double loanBalance;
    @Column
    private Double usedAmount;
    @Column
    private String installmentPlan;
}
