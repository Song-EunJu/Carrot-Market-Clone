package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.dto.*;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping(value="/users")
public class UserController {

    private UserService userService;

    @Autowired // 생성자에 autowired 적용해야 함
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원가입
//    @RequestMapping(value="/register", method= RequestMethod.GET)
    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/register")
    public String register(UserRegisterDto userLoginDto) {
        String email = userLoginDto.getEmail();
        String password = userLoginDto.getPassword();
        String name = userLoginDto.getName();
        String phone_num = userLoginDto.getPhone_num();
        String nickname = userLoginDto.getNickname();
        String profile_img = "";

        User user = new User(email, password, name, phone_num, nickname, profile_img);
        userService.save(user);
        return "redirect:/";
    }

    // 로그인
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String loginPage(Model model, UserLoginDto userLoginDto, HttpServletRequest request) {
        String email = userLoginDto.getEmail();
        String password = userLoginDto.getPassword();
        System.out.println("email + \" \"+ password = " + email + " "+ password);
        User user = userService.verify(email, password);

        if(user!=null){ // user가 있는 경우 세션 부여하고 담아서 boards로 보내기
            HttpSession session = request.getSession();
            session.setAttribute("user", new SessionUser(user));
            SessionUser sessionUser = (SessionUser)session.getAttribute("user");
            System.out.println("sessionUser = " + sessionUser);
            return "redirect:/products";
        }
        return "redirect:/";
    }

    // 마이페이지
    @GetMapping("/{userId}")
    public String mypage(Model model, @PathVariable("userId") String userId){
        Long uid = Long.parseLong(userId);
        System.out.println("uid = " + uid);
        User user = userService.findById(uid);
        System.out.println("user.getEmail() = " + user.getEmail());
        System.out.println("user.getId() = " + user.getId());
        model.addAttribute("user", user);
        return "myPage";
    }

    @GetMapping("/{userId}/profile")
    public String profile(Model model, @PathVariable("userId") String userId){
        Long uid = Long.parseLong(userId);
        User user = userService.findById(uid);
        model.addAttribute("user", user);
        return "editProfile";
    }

    @PostMapping("/{userId}/profile-nickname")
    public String editProfile(UserProfileDto userProfileDto, Model model, @PathVariable("userId") String userId){
        Long uid = Long.parseLong(userId);
        User user = userService.findById(uid);
        userService.updateNickname(userProfileDto.getNickname(), user.getId());
        model.addAttribute("user", user);
        return "myPage";
    }

    @PostMapping("/{userId}/profile-image")
    public String editProfileImg(@RequestParam("file") MultipartFile files, UserProfileImgDto userProfileDto, Model model, @PathVariable("userId") String userId) throws IOException {
        String originalName = files.getOriginalFilename();
        String path = File.separator + "profileImg";

//        String path = System.getProperty("user.home") + "\\CarrotImg"; //여기서 user.home: 사용자의 홈 디렉토리(~)
        // -> 저같은 경우에는 User/users 라서  Users/user/GaoriVideos 폴더안에 비디오가 저장됨니다

        if (!new File(path).exists()) {  //위의 폴더가 없다면 새로 생성해줌
            try {
                new File(path).mkdir();
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
        String filePath = path + "\\" + originalName; //해당 경로에 hash값 + mp4 의 파일을 새로 생성해서
        System.out.println("filePath = " + filePath);
        files.transferTo(new File(filePath));//실제로 저장시켜줌


        Long uid = Long.parseLong(userId);
        User user = userService.findById(uid);
        userService.updateProfileImg(filePath, user.getId());
        model.addAttribute("user", user);
        return "myPage";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "logout";
    }

}
