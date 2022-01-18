package com.example.demo.repository;

import com.example.demo.domain.Product;
import com.example.demo.domain.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
}