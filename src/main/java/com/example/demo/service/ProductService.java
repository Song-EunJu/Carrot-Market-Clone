package com.example.demo.service;

import com.example.demo.domain.Product;
import com.example.demo.domain.ProductImage;
import com.example.demo.domain.User;
import com.example.demo.repository.ProductImageRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;
    private ProductImageRepository productImageRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void save(Product product) {
        productRepository.save(product);
    }
    public void saveImage(ProductImage productImage) {
        productImageRepository.save(productImage);
    }
    public List<Product> findAll(){
        return productRepository.findAll();
    }
}
