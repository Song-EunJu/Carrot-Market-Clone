package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="products_image")
@AllArgsConstructor
@Builder
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO INCREMENT
    @Column
    private Long id;

    @Column(nullable = false)
    private Long product_id;

    @Column(nullable = false)
    private String original_name;

    @Column(nullable = false)
    private String save_name;

    @Column(nullable = false)
    private String image;

}

