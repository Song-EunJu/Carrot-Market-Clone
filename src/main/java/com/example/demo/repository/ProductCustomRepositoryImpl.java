package com.example.demo.repository;

import com.example.demo.domain.Product;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.demo.domain.QProduct.product;
import static com.example.demo.domain.QProductImage.productImage;

// 커스텀 인터페이스를 구현하는 클래스에 QueryDSL 쿼리 작성
//@Repository
//public class ProductCustomRepositoryImpl implements ProductCustomRepository {
//    private final JPAQueryFactory jpaQueryFactory;
//
//    public ProductCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
//        this.jpaQueryFactory = jpaQueryFactory;
//    }
////select * from products p inner join products_image pi on p.id=pi.product_id;
//    @Override
//    public void findAllInnerJoinWithProductImage() {
////        return jpaQueryFactory.selectFrom(product)
////                .innerJoin(productImage.id)
//    }
//}
