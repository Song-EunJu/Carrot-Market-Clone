package com.example.demo.repository;

import com.example.demo.domain.Product;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.demo.domain.QProduct.product;

// 커스텀 인터페이스를 구현하는 클래스에 QueryDSL 쿼리 작성
@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public ProductRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<Product> findAllInnerJoinWithProductImage() {
        return jpaQueryFactory.selectFrom(product)
                .innerJoin(product)
    }
}
