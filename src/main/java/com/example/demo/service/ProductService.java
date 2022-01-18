package com.example.demo.service;

import com.example.demo.domain.Product;
import com.example.demo.domain.ProductImage;
import com.example.demo.domain.User;
import com.example.demo.repository.ProductImageRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;
    private ProductImageRepository productImageRepository;

    public ProductService(ProductRepository productRepository, ProductImageRepository productImageRepository) {
        this.productRepository = productRepository;
        this.productImageRepository = productImageRepository;
    }

    public void save(Product product) {
        productRepository.save(product);
    }
    public void saveImage(ProductImage productImage) {
        productImageRepository.save(productImage);
    }

    public Product findById(Long product_id){
        Optional<Product> product = productRepository.findById(product_id);
        if(product.isPresent()){
            return product.get();
        }else{
            return null;
        }
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }
}
