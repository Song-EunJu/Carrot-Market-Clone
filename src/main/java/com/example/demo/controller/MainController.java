package com.example.demo.controller;

import com.example.demo.dto.SessionUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping("")
    public String firstPage(HttpServletRequest request, RedirectAttributes redirectAttributes){
        HttpSession session = request.getSession();
        SessionUser sessionUser = (SessionUser)session.getAttribute("user");
        System.out.println("sessionUser = " + sessionUser);
        if(sessionUser!=null) { // 세션 정보가 있으면 상품 페이지로 redirect
            redirectAttributes.addAttribute("user", sessionUser);
            return "redirect:/products";
        }
        else{
            System.out.println("hihi");
            return "firstPage";
        }
    }
}
