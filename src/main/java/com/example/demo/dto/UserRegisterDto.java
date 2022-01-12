package com.example.demo.dto;
import lombok.Data;
@Data
public class UserRegisterDto {
    private Long user_id;
    private String user_email;
    private String user_password;
    private String user_name;
    private String user_phone_num;
    private String user_nickname;
    private String user_loggedIn;
    private String user_profile_img;
}
