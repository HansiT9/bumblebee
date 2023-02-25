package com.system.bumblebee.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "brands")
@Data
public class BrandEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String brandName;
    @Column
    private String brandLogo;
}
