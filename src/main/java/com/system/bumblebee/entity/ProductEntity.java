package com.system.bumblebee.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "product")
@Data
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String productName;
    @Column
    private String productUrl;
    @Column
    private String productBrandName;
    @Column
    private String productCategoryName;
    @Column
    private int productQty;
}
