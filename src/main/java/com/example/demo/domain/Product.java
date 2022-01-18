package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name="products")
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO INCREMENT
    @Column
    private Long id;

    @Column(nullable = false)
    private Long user_id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private LocalDateTime posted;

    @Column(nullable = false)
    private int product_status; // 예약중 2 판매중 1 거래완료 0

    @Column(nullable = false)
    private int post_status; // 글 삭제된 경우 1->0 으로 바꿈

    @Builder
    public Product(Long user_id, String title, String category, String content, int price, LocalDateTime posted, int product_status, int post_status) {
        this.user_id = user_id;
        this.title = title;
        this.category = category;
        this.content = content;
        this.price = price;
        this.posted = posted;
        this.product_status = product_status;
        this.post_status = post_status;
    }
}
