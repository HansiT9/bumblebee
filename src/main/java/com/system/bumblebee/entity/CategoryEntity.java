package com.system.bumblebee.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity // JPA annotation to mark the class as an entity
@Table(name = "categories") // JPA annotation to map the entity to a database table
@Data // Lombok's annotation to generate getters, setters, and toString method
public class CategoryEntity {
    @Id // JPA annotation to mark the field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // JPA annotation to generate the primary key value automatically
    private Long id; // Define a private long field for id
    @Column // JPA annotation to map the field to a column in the table
    private String categoryName;
    @Column // JPA annotation to map the field to a column in the table
    private String categoryLogo;
}
