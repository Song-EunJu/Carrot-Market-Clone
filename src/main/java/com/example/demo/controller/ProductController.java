package com.example.demo.controller;

import com.example.demo.dto.SessionUser;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value="/products")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService ProductService) {
        this.productService = ProductService;
    }

    @GetMapping("") // localhost:8080/boards 뒤에 / 안쓰려면 빈 문자열 넣어주기
    public String product(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        SessionUser sessionUser = (SessionUser) session.getAttribute("user");
        System.out.println("sessionUser.getId() = " + sessionUser.getId());
        model.addAttribute("uid", sessionUser.getId());
        return "products";
    }
}
