package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.dto.UserLoginDto;
import com.example.demo.dto.UserRegisterDto;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired // 생성자에 autowired 적용해야 함
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원가입
    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/register")
    public String register(UserRegisterDto userRegisterDto) {
        String user_email = userRegisterDto.getUser_email();
        System.out.println("user_email = " + user_email);
        String user_password = userRegisterDto.getUser_password();
        String user_name = userRegisterDto.getUser_name();
        String user_phone_num = userRegisterDto.getUser_phone_num();
        String user_nickname = userRegisterDto.getUser_nickname();
        String user_profile_img = "";

        User user = new User(user_email, user_password, user_name, user_phone_num, user_nickname, user_profile_img);
        userService.save(user);
        return "redirect:/";
    }

    //로그인
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    String loginPage(Model model, UserLoginDto userLoginDto){
        return "";
    }

}
