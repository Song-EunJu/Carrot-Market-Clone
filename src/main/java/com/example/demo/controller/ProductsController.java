package com.example.demo.controller;

import com.example.demo.dto.SessionUser;
import com.example.demo.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value="/products")
public class ProductsController {
    private ProductsService productsService;

    @Autowired
    public ProductsController(ProductsService ProductsService) {
        this.productsService = ProductsService;
    }

    @GetMapping("") // localhost:8080/boards 뒤에 / 안쓰려면 빈 문자열 넣어주기
    public String products(HttpServletRequest request, Model model){
        System.out.println("gg");
        HttpSession session = request.getSession();
        SessionUser sessionUser = (SessionUser) session.getAttribute("user");
        model.addAttribute("user", sessionUser.getEmail());

//        model.addAttribute("user", sessionUser);
        return "products";
    }
}
