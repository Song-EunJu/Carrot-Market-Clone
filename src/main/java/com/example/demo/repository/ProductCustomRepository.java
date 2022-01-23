package com.example.demo.repository;

import com.example.demo.domain.Product;

import java.util.List;

public interface ProductCustomRepository {
    List<Product> findAllInnerJoinWithProductImage();
}
