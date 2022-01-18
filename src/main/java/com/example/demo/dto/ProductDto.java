package com.example.demo.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ProductDto {
    private String title;
    private String content;
    private String category;
    private int price;
    private List<MultipartFile> files;
}