package com.example.demo.controller;

import com.example.demo.domain.Product;
import com.example.demo.dto.ProductDto;
import com.example.demo.dto.SessionUser;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Controller
//@RequestMapping(value="/products")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService ProductService) {
        this.productService = ProductService;
    }

    @GetMapping("/products") // localhost:8080/boards 뒤에 / 안쓰려면 빈 문자열 넣어주기
    public String products(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        SessionUser sessionUser = (SessionUser) session.getAttribute("user");
        System.out.println("sessionUser.getId() = " + sessionUser.getId());
        model.addAttribute("uid", sessionUser.getId());
        return "products";
    }

    @GetMapping("/product")
    public String writing(){
        return "writing"; // 글 작성페이지로
    }

    @PostMapping("/product")
    public String product(HttpServletRequest request, Model model, ProductDto productDto){
        HttpSession session = request.getSession();
        SessionUser sessionUser = (SessionUser) session.getAttribute("user");

        Long user_id = sessionUser.getId();
        String title = productDto.getTitle();
        String content = productDto.getContent();
        int price = productDto.getPrice();
        LocalDateTime posted = LocalDateTime.now();
        int product_status = 1;
        int post_status = 1;
        Product product = new Product(user_id, title, content, price, posted, product_status, post_status);
        productService.save(product);
        return "products";
    }
}
