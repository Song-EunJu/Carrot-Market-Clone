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
    private String image_url;

    @Builder
    public ProductImage(Long product_id, String original_name, String image_url) {
        this.product_id = product_id;
        this.original_name = original_name;
        this.image_url = image_url;
    }
}

